package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;

@Data
@AllArgsConstructor
public class AttachmentModel {
    private int id;
    private Blob content;
    private String contentType;
    private int testId;

    public AttachmentModel(int testId) {
        this.testId = testId;
    }
}
