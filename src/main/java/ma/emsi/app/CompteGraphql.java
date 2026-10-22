package ma.emsi.app;

import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
@Controller
public class CompteGraphql {
  private final CompteService service;
  public CompteGraphql(CompteService s){service=s;}
  @QueryMapping public List<CompteResponse> comptes(){return service.all();}
  @QueryMapping public CompteResponse compte(@Argument String id){return service.get(id);}
  @MutationMapping public CompteResponse createCompte(@Argument @Valid CompteRequest input){return service.create(input);}
  @MutationMapping public CompteResponse updateCompte(@Argument String id,@Argument @Valid CompteRequest input){return service.update(id,input);}
  @MutationMapping public Boolean deleteCompte(@Argument String id){service.delete(id);return true;}
}
