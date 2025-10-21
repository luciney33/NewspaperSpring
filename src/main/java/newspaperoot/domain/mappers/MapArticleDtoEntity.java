package newspaperoot.domain.mappers;

import newspaperoot.dao.model.ArticleEntity;
import newspaperoot.dao.model.TypeEntity;
import newspaperoot.domain.model.ArticleDTO;
import newspaperoot.domain.model.TypeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapArticleDtoEntity {
    public ArticleEntity dtoToEntity(ArticleDTO dto) {
        return new ArticleEntity(
                dto.getId(),
                dto.getName(),
        new TypeEntity(dto.getTypeUI().getId(), dto.getTypeUI().getName()),
                dto.getNpaperId()
                );
    }

    public ArticleDTO entityToDto(ArticleEntity entity) {
        return new ArticleDTO(
                entity.getId(),
                entity.getName(),
                new TypeDTO(entity.getType().getId(), entity.getType().getName()),
                entity.getNPaperId(),
                0
                );
    }

    public List<ArticleDTO> entityToDtoList(List<ArticleEntity> entities) {
        List<ArticleDTO> dtos = new ArrayList<>();
        for (ArticleEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }
}
