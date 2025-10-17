package newspaperoot.domain.mappers;

import newspaperoot.dao.model.NewspaperEntity;
import newspaperoot.domain.model.NewsPaperDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapNewsDtoEntity {
    public NewsPaperDTO entityToDto(NewspaperEntity entity) {
        return new NewsPaperDTO(entity.getId(), entity.getName());
    }
    public List<NewsPaperDTO> entityListToDtoList(List<NewspaperEntity> entities) {
        List<NewsPaperDTO> dtos = new ArrayList<>();
        for (NewspaperEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }
}
