package newspaperoot.domain.mappers;
import newspaperoot.dao.model.ReaderEntity;
import newspaperoot.domain.model.ReaderDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapReaderDtoEntity {
    public ReaderDTO entityToDto(ReaderEntity entity) {
        return new ReaderDTO(entity.getId(), entity.getName(),entity.getDobReader());
    }
    public List<ReaderDTO> entityListToDtoList(List<ReaderEntity> entities) {
        List<ReaderDTO> dtos = new ArrayList<>();
        for (ReaderEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    public ReaderEntity dtoToEntity(ReaderDTO readerDTO) {
        return new ReaderEntity(readerDTO.getId(), readerDTO.getName(), readerDTO.getDobReader());
    }
}
