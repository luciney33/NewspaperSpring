package newspaperoot.domain.mappers;

import newspaperoot.dao.model.ReadActivityEntity;
import newspaperoot.domain.model.ReadActivityDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapReadActDtoEntity {
    public ReadActivityDTO entityToDto(ReadActivityEntity entity) {
        return new ReadActivityDTO(entity.getId(), entity.getIdArticle(),entity.getNameReader(), entity.getDobReader(), entity.getSubscriptionsReader(), entity.getRating());
    }
    public List<ReadActivityDTO> entityListToDtoList(List<ReadActivityEntity> entities) {
        List<ReadActivityDTO> dtos = new ArrayList<>();
        for (ReadActivityEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }
    public ReadActivityEntity dtoToEntity(ReadActivityDTO dto) {
        return new ReadActivityEntity(dto.getIdReader(), dto.getIdArticle(), dto.getNameReader(), dto.getDobReader(), dto.getSubscriptionsReader(), dto.getRating());
    }

}
