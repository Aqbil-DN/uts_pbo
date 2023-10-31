package uts.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Reel extends Post {
    private int duration;
    private int playCount;



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public void showPost(String id, User user) {
        for (Post p : user.getList_post()) {
            if (p instanceof Reel) {
                Reel reel = (Reel) p;
                if (reel.getPostId() == id) {
                    System.out.println("Post ID: " + reel.getPostId() +
                            ", Status: " + reel.getStatus() +
                            ", Type: " + reel.getPostType() +
                            ", Time Upload: " + reel.getTimeUpload() +
                            ", Duration: " + reel.getDuration() +
                            ", Play Count: " + reel.getPlayCount());
                    break;
                }
            }
        }
        System.out.println("Post not found");
    }
    public Reel(String postId, ContentState status, Timestamp timeUpload, ContentType postType,
                ArrayList<Comment> comments, int duration, int playCount) {
        super(postId, status, timeUpload, postType, comments);
        this.duration = duration;
        this.playCount = playCount;
    }
}
