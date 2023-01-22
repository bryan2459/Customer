package bryan.springframework.controllers;

import bryan.springframework.Services.CustomerService.CustomerService;
import bryan.springframework.Services.CustomerService.TransactionService;
import bryan.springframework.commands.TransValueCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class TransValueController {

    private final CustomerService customerService;
    private final TransactionService transactionService;

    public TransValueController(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }


    @GetMapping
    @RequestMapping("/person/{id}/transactions")
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Getting Transactions list for transValue id: " + id);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("person", customerService.findCommandById(Long.valueOf(String.valueOf(id))));

        return "person/transaction/list";
    }

    //http://localhost:8080/person/1/transaction/2/show
    @GetMapping
    @RequestMapping("person/{personId}/transaction/{id}/show")
    public String showPersonTransaction(@PathVariable String personId,
                                       @PathVariable String id, Model model){
        model.addAttribute("transValue", transactionService.findByPersonIdAndTransactionId(Long.valueOf(personId), Long.valueOf((id))));
        return "person/transaction/show";
    }
    @GetMapping
    @RequestMapping("person/{personId}/transaction/{id}/update")
    public String updatePersonTransactions(@PathVariable String personId,
                                         @PathVariable String id, Model model){
        System.out.println("from update: "+ personId);
        model.addAttribute("transValue", transactionService.findByPersonIdAndTransactionId(Long.valueOf(personId), Long.valueOf(id)));


        return "person/transaction/transform";
    }

    @PostMapping("person/{personId}/transaction")
    public String saveOrUpdate(@ModelAttribute TransValueCommand command){

        System.out.println("Controller: "+command.getPersonId());
        TransValueCommand savedCommand = transactionService.saveTransCommand(command);

        log.debug("saved person id:" + savedCommand.getPersonId());
        log.debug("saved transaction id:" + savedCommand.getId());

        return "redirect:/person/" + savedCommand.getPersonId() + "/transaction/" + savedCommand.getId() + "/show";
    }
}
