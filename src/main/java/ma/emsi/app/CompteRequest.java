package ma.emsi.app;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CompteRequest(@NotNull @DecimalMin("0.00") BigDecimal balance,
 @NotBlank @Pattern(regexp="[A-Z]{3}") String currency, @NotNull Compte.AccountType type) {}
