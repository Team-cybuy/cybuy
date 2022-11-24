package cybuy.postalCode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/postal")
public class PostalCodeController {

    WebClient webClient;
    public PostalCodeController() {

        this.webClient = WebClient.builder()
                .baseUrl("http://api.zippopotam.us/de/")
                .build();
    }

    @GetMapping(value = "/{code}", produces = "application/json")
    public Mono<String> resolvePostalCode(@PathVariable("code") String code) {

        return this.webClient.get()
                .uri(code)
                .retrieve()
                .bodyToMono(String.class);
    }
}
