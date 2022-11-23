package cybuy.postalCode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/postal")
public class PostalCodeController {

    private final WebClient webClient;

    public PostalCodeController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://api.zippopotam.us/de/")
                .build();
    }

    @GetMapping("/server/{code}")
    public ResponseEntity<String> resolvePostalCode(@PathVariable("code") String code) throws JsonProcessingException { // this method will be removed and only exists for implementation tests

        ResponseEntity<String> response = this.webClient.get()
                .uri(code)
                .retrieve()
                .toEntity(String.class)
                .block();

        if(response.getStatusCode().equals(HttpStatus.OK)) {

            JsonNode json = new JsonMapper().readTree(response.getBody());
            String place = json.get("places").get(0).get("place name").toString().replace("\"", "");
            return new ResponseEntity<>(place, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, response.getStatusCode());
        }
    }

    @GetMapping("/{code}")
    public Mono<ResponseEntity<String>> resolvePostalCodeData(@PathVariable("code") String code) {

        return this.webClient.get()
                .uri(code)
                .retrieve()
                .toEntity(String.class);
    }
}
