package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UserResponse[] response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserResponse {
        private long id;
        private String first_name;
        private String last_name;
        private String userNameDelimiter = " ";

        public String getUserName() {
            return first_name + userNameDelimiter + last_name;
        }
    }
}
