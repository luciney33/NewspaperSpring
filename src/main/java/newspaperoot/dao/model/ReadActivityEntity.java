package newspaperoot.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ReadActivityEntity {
    private int idArticle;
    private int id;
    private String nameReader;
    private LocalDate dobReader;
    private List<String> subscriptionsReader;
    private int rating;

}
