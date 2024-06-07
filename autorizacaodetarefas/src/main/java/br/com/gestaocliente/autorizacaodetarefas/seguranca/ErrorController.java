package br.com.gestaocliente.autorizacaodetarefas.seguranca;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Controller
public class ErrorController {

    @RequestMapping("/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handle403() {
        return "error/403";  // Retorna a página de erro 403
    }
}
