package ma.emsi.app;

import java.math.BigDecimal;
import java.time.LocalDate;
public record CompteResponse(String id,LocalDate createdAt,BigDecimal balance,String currency,Compte.AccountType type) {}
