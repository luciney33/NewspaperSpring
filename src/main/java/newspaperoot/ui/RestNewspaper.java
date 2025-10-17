package newspaperoot.ui;

import com.newspapercrud.domain.model.NewspaperUI;
import com.newspapercrud.domain.model.TypeUI;
import com.newspapercrud.domain.service.NewspaperService;
import com.newspapercrud.domain.service.TypeService;
import newspaperoot.domain.model.NewsPaperDTO;
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
        return newspaperService.getAll();
    }
}

