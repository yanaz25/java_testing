package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogModel {
    private int id;
    private String content;
    private int isException;
    private int testId;

    public LogModel(int testId) {
        this.testId = testId;
    }
}
