package ma.emsi.app;

import org.springframework.context.annotation.*;
import org.springframework.boot.CommandLineRunner;
import java.math.BigDecimal;
@Configuration
public class SeedData {
 @Bean CommandLineRunner seed(CompteService s){return args->{s.create(new CompteRequest(new BigDecimal("2500.00"),"MAD",Compte.AccountType.CURRENT));s.create(new CompteRequest(new BigDecimal("7200.00"),"EUR",Compte.AccountType.SAVING));};}
}
