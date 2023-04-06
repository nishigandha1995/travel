import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hotel {
    @GetMapping("/myhote")
    public String getData(){
        return "Please book my hotel";
    }

}