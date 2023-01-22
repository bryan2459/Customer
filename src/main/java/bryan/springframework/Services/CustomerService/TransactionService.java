package bryan.springframework.Services.CustomerService;

import bryan.springframework.commands.TransValueCommand;

public interface TransactionService {

    TransValueCommand findByPersonIdAndTransactionId(Long personId, Long transactionId);
    TransValueCommand saveTransCommand(TransValueCommand command);
}
