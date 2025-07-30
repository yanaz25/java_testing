package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    PostResponse response;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PostResponse {
        private long ownerId;
        @JsonProperty("post_id")
        private int postId;
        private String message;
        private String attachment;

        public PostResponse(long ownerId, String message) {
            this.ownerId = ownerId;
            this.message = message;
        }
    }
}
