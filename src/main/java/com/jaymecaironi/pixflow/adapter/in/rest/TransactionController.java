package com.jaymecaironi.pixflow.adapter.in.rest;

import com.jaymecaironi.pixflow.adapter.in.rest.dto.CreateTransactionRequest;
import com.jaymecaironi.pixflow.adapter.in.rest.dto.TransactionResponse;
import com.jaymecaironi.pixflow.application.port.in.CreateTransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    
    private final CreateTransactionUseCase createTransaction;

    public TransactionController(CreateTransactionUseCase createTransaction) {
        this.createTransaction = createTransaction;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(@Valid @RequestBody CreateTransactionRequest req) {
        var tx = createTransaction.create(req.originKey(), req.destinationKey(), req.amount());
        return TransactionResponse.from(tx);
    }    
}
