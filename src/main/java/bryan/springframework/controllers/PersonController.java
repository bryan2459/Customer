package bryan.springframework.controllers;

import bryan.springframework.Services.CustomerService.CustomerService;
import bryan.springframework.commands.PersonCommand;
import bryan.springframework.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class PersonController {

    private final CustomerService customerService;
    private static final String RECIPE_RECIPEFORM_URL = "person/personform";

    public PersonController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/person/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("person", customerService.findById(Long.valueOf(id)));
        return "person/show";
    }

    @RequestMapping("/person/new")
    public String newPerson(Model model){
        model.addAttribute("person", new PersonCommand());

        return "person/personform";
    }

    @RequestMapping("person/{id}/update")
    public String updatePeron(@PathVariable String id, Model model){
        model.addAttribute("person", customerService.findCommandById(Long.valueOf(id)));
        return  "person/personform";
    }

    @PostMapping("person")
    public String saveOrUpdate(@Valid @ModelAttribute("person") PersonCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }
        PersonCommand savedCommand = customerService.savePersonCommand(command);

        return "redirect:/person/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("person/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        customerService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);

        return modelAndView;
    }




}
