package newspaperoot.domain.service;

import lombok.Data;
import newspaperoot.dao.TypeRepository;
import newspaperoot.dao.model.TypeEntity;
import newspaperoot.domain.mappers.MapTypeDtoEntity;
import newspaperoot.domain.model.TypeDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class TypeService {
    private final TypeRepository typeRepository;
    private final MapTypeDtoEntity mapper;

    public TypeService(TypeRepository typeRepository, MapTypeDtoEntity mapper) {
        this.typeRepository = typeRepository;
        this.mapper = mapper;
    }

    public List<TypeDTO> getAllTypes() {
        List<TypeEntity> typeEntities = typeRepository.getAllTypes();
        return mapper.entityToDtoList(typeEntities);
    }
}
