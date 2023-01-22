package bryan.springframework.controllers;

import bryan.springframework.Services.CustomerService.CustomerService;
import bryan.springframework.domain.Category;
import bryan.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    private final CustomerService customerService;

    public IndexController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        model.addAttribute ("persons", customerService.getCustomers());


        return "index";
    }
}
