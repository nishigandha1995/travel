import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class flight {
    @GetMapping("/myflight")
    public String getData(){
        return "Please book your  Flight ticket, Choose Your Fav Airlines Idigo";
    }

}