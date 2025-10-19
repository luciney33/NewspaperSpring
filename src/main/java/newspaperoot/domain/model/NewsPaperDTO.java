package newspaperoot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class NewsPaperDTO {
    private int id;
    private String name;
}
