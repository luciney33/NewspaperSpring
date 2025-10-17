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
        return articleService.getArticles();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/articles")
    public int addArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.addArticle(articleDTO);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/articles")
    public void updateArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.updateArticle(articleDTO);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping("/articles/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int articleId, @RequestParam(required = false) boolean confirm) {
        articleService.deleteArticle(articleId,confirm);
    }
}