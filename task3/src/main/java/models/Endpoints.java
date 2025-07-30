package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endpoints {
    private String posts;
    private String postNum;
    private String users;
    private String userNum;
}
