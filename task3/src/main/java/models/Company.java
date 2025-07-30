package models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
