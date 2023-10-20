package uts.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Feed extends Post{
    private String caption;
    private int likes;



    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void showPost(String id, User user){
        for (Post p : user.getList_post()) {
            if(p instanceof Feed){
                Feed feed = (Feed) p;
                if(feed.getPostId()==id){
                    System.out.println("Post ID: " + feed.getPostId() +
                            ", Status: " + feed.getStatus() +
                            ", Type: " + feed.getPostType() +
                            ", Time Upload: " + feed.getTimeUpload() +
                            ", Caption: " + feed.getCaption() +
                            ", Likes: " + feed.getLikes());
                    break;
                }
            }
        }
        System.out.println("tidak ditemukan");
    }
    public Feed(String postId, ContentState status, Timestamp timeUpload, ContentType postType,
                ArrayList<Comment> comments, String caption, int likes) {
        super(postId, status, timeUpload, postType, comments);
        this.caption = caption;
        this.likes = likes;
    }
}