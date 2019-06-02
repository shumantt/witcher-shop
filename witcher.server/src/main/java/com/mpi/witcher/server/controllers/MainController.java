package com.mpi.witcher.server.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "forward:/static/index.html";
    }
}

/*@Component
@ControllerAdvice
class Customizer {
    @ExceptionHandler(NoHandlerFoundException.class)
    public RedirectView notFound() {
        return new RedirectView("/");
    }

}*/
