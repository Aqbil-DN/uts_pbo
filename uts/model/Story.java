package uts.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Story extends Post {
    private int duration;
    private int view;



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public void showPost(String id, User user) {
    List<Post> listPost = user.getList_post();
    for (int i = 0; i < listPost.size(); i++) {
        Post p = listPost.get(i);
        if (p instanceof Story) {
            Story story = (Story) p;
            if (story.getPostId().equals(id)) {
                System.out.println("Post ID: " + story.getPostId() +
                        ", Status: " + story.getStatus() +
                        ", Type: " + story.getPostType() +
                        ", Time Upload: " + story.getTimeUpload() +
                        ", Duration: " + story.getDuration() +
                        ", Views: " + story.getView());
                return; // Exit the loop when the post is found
            }
        }
    }
    System.out.println("Post not found");
}

    public Story(String postId, ContentState status, Timestamp timeUpload, ContentType postType,
                 ArrayList<Comment> comments, int duration, int view) {
        super(postId, status, timeUpload, postType, comments);
        this.duration = duration;
        this.view = view;
    }
}
