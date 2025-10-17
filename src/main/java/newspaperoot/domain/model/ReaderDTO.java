package newspaperoot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class ReaderDTO {
    private int id;
    private String name;
    private LocalDate birthday;
}
