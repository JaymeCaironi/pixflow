package com.jaymecaironi.pixflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.jaymecaironi.pixflow.adapter.out.persistence.AccountJpaEntity;
import com.jaymecaironi.pixflow.adapter.out.persistence.SpringDataAccountRepositoy;
import com.jaymecaironi.pixflow.application.port.out.AccountRepository;

@SpringBootTest
@Import(TestcontainersConfig.class)
class AccountConcurrencyTest {
    
    @Autowired AccountRepository accountRepository;
    @Autowired SpringDataAccountRepositoy springData;

    @BeforeEach
    void semearConta() {
        springData.deleteAll();
        springData.save(new AccountJpaEntity(UUID.randomUUID(), "teste@pix.com", new BigDecimal("500.00")));
    }

    @Test
    void apenasCincoDebitosDevemPassarComSaldoDe500() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger successes = new AtomicInteger();

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                if (accountRepository.debit("teste@pix.com", new BigDecimal("100.00"))) {
                    successes.incrementAndGet();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(5, successes.get());
        assertEquals(new BigDecimal("0.00"),
            accountRepository.findBalance("teste@pix.com").orElseThrow().setScale(2));
    }
}
