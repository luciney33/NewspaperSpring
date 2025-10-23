package newspaperoot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDTO {
    private String username;
    private String password;
}
