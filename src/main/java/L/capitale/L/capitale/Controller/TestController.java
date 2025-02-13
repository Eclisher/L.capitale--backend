package L.capitale.L.capitale.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/ping")
    public String index() {
        return "pong";
    }

    @GetMapping("/")
    public String welcome() {
        return "Votre serveur est bien demmarer";
    }
}
