package ma.emsi.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest @AutoConfigureMockMvc
class BankIntegrationTest {
 @Autowired MockMvc mvc; @Autowired ObjectMapper json; @Autowired CompteRepository repo;
 @Test void restCrudAndDao() throws Exception {
  String body="{\"balance\":500,\"currency\":\"MAD\",\"type\":\"CURRENT\"}";
  var result=mvc.perform(post("/api/comptes").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andReturn();
  String id=json.readTree(result.getResponse().getContentAsString()).get("id").asText();
  org.assertj.core.api.Assertions.assertThat(repo.findById(id)).isPresent();
  mvc.perform(put("/api/comptes/"+id).contentType(MediaType.APPLICATION_JSON).content(body.replace("500","700"))).andExpect(jsonPath("$.balance").value(700));
  mvc.perform(delete("/api/comptes/"+id)).andExpect(status().isNoContent());
  mvc.perform(get("/api/comptes/"+id)).andExpect(status().isNotFound());
 }
 @Test void rejectsInvalidAndExposesProjection() throws Exception {
  mvc.perform(post("/api/comptes").contentType(MediaType.APPLICATION_JSON).content("{\"balance\":-1,\"currency\":\"x\",\"type\":\"CURRENT\"}")).andExpect(status().isBadRequest());
  mvc.perform(get("/data/comptes?projection=summary")).andExpect(status().isOk()).andExpect(jsonPath("$._embedded.comptes[0].currency").exists());
 }
 @Test void graphQlMutationAndQuery() throws Exception {
  String query="mutation { createCompte(input:{balance:42,currency:\"MAD\",type:SAVING}) { id balance currency } }";
  mvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(java.util.Map.of("query",query)))).andExpect(status().isOk()).andExpect(jsonPath("$.errors").doesNotExist()).andExpect(jsonPath("$.data.createCompte.balance").value(42));
  mvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON).content("{\"query\":\"{ comptes { id balance type } }\"}")).andExpect(jsonPath("$.data.comptes").isArray());
 }
}
