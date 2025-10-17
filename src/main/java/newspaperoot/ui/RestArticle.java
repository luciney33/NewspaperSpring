package newspaperoot.ui;


import newspaperoot.domain.model.ArticleDTO;
import newspaperoot.domain.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestArticle {
    private final ArticleService articleService;
    public RestArticle(ArticleService articleService) {
        this.articleService = articleService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/articles")
    public List<ArticleDTO> getArticles() {
        return articleService.getAllArticles();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/articles")
    public ArticleDTO addArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.saveArticle(articleDTO);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/articles")
    public void updateArticle(@RequestBody ArticleDTO articleDTO, @RequestParam String newName) {
        articleService.updateArticle(articleDTO, newName);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping("/articles/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int articleId, @RequestParam(required = false) boolean confirm) {
        articleService.deleteArticle(articleId,confirm);
    }
}