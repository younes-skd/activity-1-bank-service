package ma.emsi.app;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
public class Compte {
  @Id @GeneratedValue(strategy=GenerationType.UUID) private String id;
  private LocalDate createdAt = LocalDate.now();
  @Column(nullable=false,precision=19,scale=2) private BigDecimal balance;
  @Column(nullable=false) private String currency;
  @Enumerated(EnumType.STRING) private AccountType type;
  public enum AccountType { CURRENT, SAVING }
  public Compte() {}
  public String getId(){return id;} public void setId(String v){id=v;}
  public LocalDate getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDate v){createdAt=v;}
  public BigDecimal getBalance(){return balance;} public void setBalance(BigDecimal v){balance=v;}
  public String getCurrency(){return currency;} public void setCurrency(String v){currency=v;}
  public AccountType getType(){return type;} public void setType(AccountType v){type=v;}
}
