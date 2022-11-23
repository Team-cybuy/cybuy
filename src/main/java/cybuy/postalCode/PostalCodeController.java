package cybuy.postalCode;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
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

//    @GetMapping("/server/{code}")
//    public ResponseEntity<String> resolvePostalCode(@PathVariable("code") String code) {
//
//        ResponseEntity<String> response = this.webClient.get()
//                .uri(code)
//                .retrieve()
//                .toEntity(String.class)
//                .block();
//
//        if(response.getStatusCode().equals(HttpStatus.OK)) {
//
//            JsonParser parser = JsonParserFactory.getJsonParser();
//            String places = parser.parseMap(response.getBody()).get("places").toString().replace("[", "").replace("]", "");
//            return new ResponseEntity<>(parser.parseMap(places).get("place name").toString(), HttpStatus.OK);
//        } else {
//
//            return new ResponseEntity<>(null, response.getStatusCode());
//        }
//    }

    @GetMapping("/{code}")
    public Mono<ResponseEntity<String>> resolvePostalCodeData(@PathVariable("code") String code) {

        return this.webClient.get()
                .uri(code)
                .retrieve()
                .toEntity(String.class);
    }
}
