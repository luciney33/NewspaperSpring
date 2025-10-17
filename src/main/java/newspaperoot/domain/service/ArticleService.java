package newspaperoot.domain.service;

import lombok.Data;
import newspaperoot.dao.ArticleRepository;
import newspaperoot.dao.model.ArticleEntity;
import newspaperoot.domain.mappers.MapArticleDtoEntity;
import newspaperoot.domain.model.ArticleDTO;
import newspaperoot.domain.model.TypeDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MapArticleDtoEntity mapper;

    public ArticleService(ArticleRepository articleRepository, MapArticleDtoEntity mapper ) {
        this.articleRepository = articleRepository;
        this.mapper = mapper;
    }


    public List<ArticleDTO> getAllArticles() {
        List<ArticleEntity> articles = articleRepository.getAll();
        return mapper.entityToDtoList(articles);
    }
    public ArticleDTO getArticleById(int id) {
        ArticleEntity articleEntity = articleRepository.get(id);
        return mapper.entityToDto(articleEntity);
    }
    public ArticleDTO saveArticle(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = mapper.dtoToEntity(articleDTO);
        int id = articleRepository.save(articleEntity);
        articleDTO.setId(id);
        return articleDTO;
    }

    public ArticleDTO updateArticle(ArticleDTO articleDTO, String newName) {
        ArticleEntity articleEntity = mapper.dtoToEntity(articleDTO);
        articleRepository.update(articleEntity, newName);
        articleDTO.setName(newName);
        return articleDTO;
    }

    public void deleteArticle(int articleId, boolean confirmation) {
        ArticleEntity articleEntity = articleRepository.get(articleId);
        if (articleEntity == null) {
            throw new RuntimeException("Article not found with id: " + articleId);
        }
        articleRepository.delete(articleEntity,confirmation);
    }
}
