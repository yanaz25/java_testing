package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Post(int userId, int id) {
        this.userId = userId;
        this.id = id;
    }

    public boolean isEqualExceptId(Object post) {
        if (this == post) {
            return true;
        }
        if (post == null || getClass() != post.getClass()) {
            return false;
        }
        Post that = (Post) post;
        return userId == that.userId &&
                title.equals(that.title) &&
                body.equals(that.body);
    }

    public static boolean isPostsOrderedById(List<Post> posts) {
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i).getId() > posts.get(i + 1).getId()) {
                return false;
            }
        }
        return true;
    }

    public boolean isEqualIdAndNonEmpty(Post post) {
        if (post == null) {
            return false;
        }
        return this.id == post.id && this.userId == post.userId &&
                !post.title.isEmpty() && !post.body.isEmpty();
    }
}


