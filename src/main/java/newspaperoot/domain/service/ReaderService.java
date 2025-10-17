package newspaperoot.domain.service;

import lombok.Data;
import newspaperoot.dao.ReaderRepository;
import newspaperoot.dao.model.CredentialEntity;
import newspaperoot.dao.model.ReaderEntity;
import newspaperoot.domain.mappers.MapReaderDtoEntity;
import newspaperoot.domain.model.CredentialDTO;
import newspaperoot.domain.model.ReaderDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final MapReaderDtoEntity mapper;

    public ReaderService(ReaderRepository readerRepository, MapReaderDtoEntity mapper) {
        this.readerRepository = readerRepository;
        this.mapper = mapper;
    }

    public List<ReaderDTO> getAllReaders() {
        List<ReaderEntity> readerEntities = readerRepository.getAll();
        return mapper.entityListToDtoList(readerEntities);

    }

    public ReaderDTO getReaderById(int id) {
        ReaderEntity reader = readerRepository.getReaderById(id);
        return mapper.entityToDto(reader);
    }
    public ReaderDTO saveReader(ReaderDTO readerDTO, CredentialDTO credentialDTO, boolean confirmation) {
        ReaderEntity readerEntity = mapper.dtoToEntity(readerDTO);
        CredentialEntity credentialEntity = new CredentialEntity(
                credentialDTO.getUsername(),
                credentialDTO.getPassword(),
                readerEntity.getId()
        );

        readerRepository.save(readerEntity, credentialEntity, confirmation);

        return readerDTO;
    }
    public ReaderDTO deleteReader(ReaderDTO readerDTO, boolean confirmation) {
        ReaderEntity readerEntity = mapper.dtoToEntity(readerDTO);
        readerRepository.delete(readerEntity, confirmation);
        return readerDTO;
    }

    public ReaderDTO updateReader(ReaderDTO readerDTO, String newName) {
        ReaderEntity readerEntity = mapper.dtoToEntity(readerDTO);
        readerRepository.update(readerEntity, newName);
        readerDTO.setName(newName);
        return readerDTO;
    }

}
