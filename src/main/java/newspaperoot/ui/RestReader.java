package newspaperoot.ui;

import com.newspapercrud.domain.model.ReaderArticleUI;
import com.newspapercrud.domain.model.ReaderUI;
import com.newspapercrud.domain.service.ReadArticleService;
import com.newspapercrud.domain.service.ReaderService;
import newspaperoot.domain.model.ReaderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RestReader {
    private final ReaderService readerService;
    private final ReadArticleService readArticleService;

    public RestReader(ReaderService readerService, ReadArticleService readArticleService) {
        this.readerService = readerService;
        this.readArticleService = readArticleService;
    }

    @GetMapping("/readers")
    public List<ReaderDTO> getAll() {
        return readerService.getAll();
    }
    @GetMapping("/articles/{articleId}/readers")
    public List<ReaderArticleUI> getReadersByIdArticle(@PathVariable int articleId) {
        return readerService.getAllReadersByArticle(articleId);
    }

    @GetMapping("/articles/{readerId}/reader")
    public ReaderArticleUI getReader(@PathVariable int readerId) {
        return readerService.get(readerId);
    }

    @PostMapping("/articles/readers")
    public int addReadArticle(@RequestBody ReaderArticleUI readerArticleUI) {
        return readArticleService.addReadArticle(readerArticleUI);

    }

    @DeleteMapping("/articles/readers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReadArticle(@RequestBody ReaderArticleUI readArticleUI) {
        return readArticleService.delete(readArticleUI);
    }

    @PutMapping("/articles/readers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReadArticle(@RequestBody ReaderArticleUI readerArticleUI) {
        readArticleService.updateReadArticle(readerArticleUI);
    }

}
