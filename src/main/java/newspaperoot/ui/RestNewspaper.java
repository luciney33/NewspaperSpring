package newspaperoot.ui;
import newspaperoot.domain.model.NewsPaperDTO;
import newspaperoot.domain.service.NewspaperService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RestNewspaper {
    private final NewspaperService newspaperService;

    public RestNewspaper(NewspaperService newspaperService) {
        this.newspaperService = newspaperService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/newspapers")
    public List<NewsPaperDTO> getAll() {
        return newspaperService.getAllNewspapers();
    }
}

