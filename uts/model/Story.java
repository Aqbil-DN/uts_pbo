package uts.model;

import java.sql.Timestamp;
import java.util.ArrayList;

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
        for (Post p : user.getList_post()) {
            if (p instanceof Story) {
                Story story = (Story) p;
                if (story.getPostId() == id) {
                    System.out.println("Post ID: " + story.getPostId() +
                            ", Status: " + story.getStatus() +
                            ", Type: " + story.getPostType() +
                            ", Time Upload: " + story.getTimeUpload() +
                            ", Duration: " + story.getDuration() +
                            ", Views: " + story.getView());
                    break;
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
