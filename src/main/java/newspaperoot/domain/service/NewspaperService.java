package newspaperoot.domain.service;

import lombok.Data;
import newspaperoot.dao.NewspaperRepository;
import newspaperoot.dao.model.NewspaperEntity;
import newspaperoot.domain.mappers.MapNewsDtoEntity;
import newspaperoot.domain.model.NewsPaperDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class NewspaperService {
    private final NewspaperRepository newspaperRepository;
    private final MapNewsDtoEntity mapper;

    public NewspaperService(NewspaperRepository newspaperRepository, MapNewsDtoEntity mapper) {
        this.newspaperRepository = newspaperRepository;
        this.mapper = mapper;
    }

    public List<NewsPaperDTO> getAllNewspapers() {
        List<NewspaperEntity> newspaperEntities = newspaperRepository.getAll();
        return mapper.entityListToDtoList(newspaperEntities);

    }
}
