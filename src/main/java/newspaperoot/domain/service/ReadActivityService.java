package newspaperoot.domain.service;

import newspaperoot.dao.ReaderActivityRepository;
import newspaperoot.dao.model.ReadActivityEntity;
import newspaperoot.domain.Error.AppError;
import newspaperoot.domain.mappers.MapReadActDtoEntity;
import newspaperoot.domain.model.ReadActivityDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadActivityService {
    private final ReaderActivityRepository rActRepository;
    private final MapReadActDtoEntity mapper;

    public ReadActivityService(ReaderActivityRepository rActRepository, MapReadActDtoEntity mapper) {
        this.rActRepository = rActRepository;
        this.mapper = mapper;
    }

    public List<ReadActivityDTO> getReadersOfArticle(int idArticle) {
        List<ReadActivityEntity> entities = rActRepository.getReadersArticle(idArticle);
        return mapper.entityListToDtoList(entities);
    }

    public int addRating(int readerId, int articleId, int rating) {
        ReadActivityEntity entity = new ReadActivityEntity(readerId, articleId, null, null, null, rating);
        return rActRepository.addRating(entity);
    }
    public ReadActivityDTO modifyRating(ReadActivityDTO dto) {
        ReadActivityEntity entity = mapper.dtoToEntity(dto);
        rActRepository.updateRating(entity);
        return dto;
    }
    public void deleteRating(int readerId, int articleId) {
        rActRepository.deleteRating(readerId, articleId);
    }
}
