package ma.emsi.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
@Service @Transactional
public class CompteService {
  private final CompteRepository repo; private final CompteMapper mapper;
  public CompteService(CompteRepository r,CompteMapper m){repo=r;mapper=m;}
  public List<CompteResponse> all(){return repo.findAll().stream().map(mapper::response).toList();}
  public CompteResponse get(String id){return mapper.response(entity(id));}
  private Compte entity(String id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));}
  public CompteResponse create(CompteRequest r){Compte c=new Compte();mapper.update(c,r);return mapper.response(repo.save(c));}
  public CompteResponse update(String id,CompteRequest r){Compte c=entity(id);mapper.update(c,r);return mapper.response(repo.save(c));}
  public void delete(String id){repo.delete(entity(id));}
}
