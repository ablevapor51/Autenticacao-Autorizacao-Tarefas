import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/Clientes/{ClienteId}")
public class ClienteController {
    
    @GetMapping
    public String getCliente(@PathVariable String ClienteId) {
        return "Detalhes do cliente com ID: " + ClienteId;
    }
    
}
