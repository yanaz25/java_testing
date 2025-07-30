package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDataModel {
    private int maxNumOfTests;
    private int randomValueOrigin;
    private int randomValueBound;
}
