package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePhotoOnWall {
    private SavePhotoOnWallResponse[] response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SavePhotoOnWallResponse {
        @JsonProperty("owner_id")
        private long ownerId;
        @JsonProperty("id")
        private long photoId;
    }
}
