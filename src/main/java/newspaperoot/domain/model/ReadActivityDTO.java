package newspaperoot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
public class ReadActivityDTO {
    private int idArticle;
    private int idReader;
    private String nameReader;
    private LocalDate dobReader;
    private List<String> subscriptionsReader;
    private int rating;
}
