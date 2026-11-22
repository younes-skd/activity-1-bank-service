package ma.emsi.app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
@RestController @RequestMapping("/api/comptes")
public class CompteController {
  private final CompteService service;
  public CompteController(CompteService s){service=s;}
  @GetMapping public List<CompteResponse> all(){return service.all();}
  @GetMapping("/{id}") public CompteResponse get(@PathVariable String id){return service.get(id);}
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public CompteResponse create(@Valid @RequestBody CompteRequest r){return service.create(r);}
  @PutMapping("/{id}") public CompteResponse update(@PathVariable String id,@Valid @RequestBody CompteRequest r){return service.update(id,r);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable String id){service.delete(id);}
}
