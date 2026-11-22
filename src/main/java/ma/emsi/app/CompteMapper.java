package ma.emsi.app;

import org.springframework.stereotype.Component;
@Component
public class CompteMapper {
  public CompteResponse response(Compte c){return new CompteResponse(c.getId(),c.getCreatedAt(),c.getBalance(),c.getCurrency(),c.getType());}
  public void update(Compte c,CompteRequest r){c.setBalance(r.balance()); c.setCurrency(r.currency()); c.setType(r.type());}
}
