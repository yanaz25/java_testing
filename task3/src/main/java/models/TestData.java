package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {
    private User user;
    private String emptyJson;
    private int postNum;
    private int userId;
    private int wrongPostNum;
    private int userNum;
    private String codeAssertion;
    private int myPostUserId;
    private int textLength;
    private String contentTypeAttr;
    private String idAttr;
}
