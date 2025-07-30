package models.resourcesModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endpoints {
    private String addPost;
    private String deletePost;
    private String users;
    private String editPost;
    private String uploadPhotoUrl;
    private String saveWallPhoto;
    private String createComment;
    private String likes;
    private String photo;
}
