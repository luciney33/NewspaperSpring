package newspaperoot.domain.service;


import lombok.Data;
import newspaperoot.dao.CredentialRepository;
import newspaperoot.dao.model.CredentialEntity;
import newspaperoot.domain.model.CredentialDTO;

import org.springframework.stereotype.Service;

// llamar al repository pero tambien crea credentiadto a credential normal
@Data
@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean checkLogin(CredentialDTO credentialDTO) {
        CredentialEntity credentialEntity = credentialRepository.get(credentialDTO.getUsername());
        return credentialEntity.getPassword().equals(credentialDTO.getPassword());

    }
}
