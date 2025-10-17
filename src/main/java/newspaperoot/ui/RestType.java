package newspaperoot.ui;

import com.newspapercrud.domain.model.ArticleUI;
import com.newspapercrud.domain.model.TypeUI;
import com.newspapercrud.domain.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestType {
    private final TypeService typeService;

    public RestType(TypeService typeService) {
        this.typeService = typeService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/types")
    public List<TypeUI> getAll() {
        return typeService.getAll();
    }
}

