package newspaperoot.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeEntity {
    private int id;
    private String name;
    private String description;

}
