package ma.emsi.app;

import org.springframework.data.rest.core.config.Projection;
import java.math.BigDecimal;
@Projection(name="summary",types=Compte.class)
public interface CompteSummary { String getId(); BigDecimal getBalance(); String getCurrency(); }
