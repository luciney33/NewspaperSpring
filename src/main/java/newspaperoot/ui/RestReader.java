package newspaperoot.ui;
import newspaperoot.domain.model.CredentialDTO;
import newspaperoot.domain.model.ReadActivityDTO;
import newspaperoot.domain.model.ReaderDTO;
import newspaperoot.domain.service.ReaderService;
import newspaperoot.domain.service.ReadActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RestReader {
    private final ReaderService readerService;
    private final ReadActivityService readArticleService;

    public RestReader(ReaderService readerService, ReadActivityService readArticleService) {
        this.readerService = readerService;
        this.readArticleService = readArticleService;
    }

    @GetMapping("/readers")
    public List<ReaderDTO> getAll() {
        return readerService.getAllReaders();
    }
    @GetMapping("/articles/{articleId}/readers")
    public List<ReadActivityDTO> getReadersByIdArticle(@PathVariable int articleId) {
        return readArticleService.getReadersOfArticle(articleId);
    }

    @GetMapping("/articles/{readerId}/reader")
    public ReaderDTO getReader(@PathVariable int readerId) {
        return readerService.getReaderById(readerId);
    }

    @PostMapping("/articles/readers")
    public ReaderDTO addReadArticle(@RequestBody ReaderDTO reader, CredentialDTO credential,@RequestParam(required = false) boolean confirmation) {
        return readerService.saveReader(reader,credential,confirmation);

    }

    @DeleteMapping("/articles/readers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReaderDTO deleteReadArticle(@RequestBody ReaderDTO reader, @RequestParam(required = false) boolean confirmation) {
        return readerService.deleteReader(reader,confirmation);
    }

    @PutMapping("/articles/readers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReaderDTO updateReadArticle(@RequestBody ReaderDTO readerDTO, @RequestParam String newName) {
        return readerService.updateReader(readerDTO, newName);
    }

}
