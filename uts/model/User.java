package uts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String userID;
    private String username;
    private String password;
    private String bio;
    private ArrayList<Post> list_post;



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Post> getList_post() {
        return list_post;
    }

    public void setList_post(ArrayList<Post> list_post) {
        this.list_post = list_post;
    }

    public void showUserPosts() {
        ArrayList<Post> sortedPosts = new ArrayList<>(list_post);

        Collections.sort(sortedPosts, (post1, post2) -> {
            ContentState state1 = post1.getStatus();
            ContentState state2 = post2.getStatus();

            if (state1 != state2) {
                List<ContentState> order = List.of(ContentState.PINNED, ContentState.SHOWED, ContentState.ARCHIVED,
                        ContentState.DELETED);
                return order.indexOf(state1) - order.indexOf(state2);
            } else {
                return post2.getTimeUpload().compareTo(post1.getTimeUpload());
            }
        });

        int deletedPostCount = 0;
for (int i = 0; i < sortedPosts.size(); i++) {
    Post post = sortedPosts.get(i);
    ContentState state = post.getStatus();
    if (state == ContentState.DELETED) {
        deletedPostCount++;
    } else {
        if (post instanceof Reel) {
            Reel reel = (Reel) post;
            System.out.println("Post ID: " + reel.getPostId() +
                    ", Status: " + reel.getStatus() +
                    ", Type: " + reel.getPostType() +
                    ", Time Upload: " + reel.getTimeUpload() +
                    ", Play Count: " + reel.getPlayCount());
        } else if (post instanceof Feed) {
            Feed feed = (Feed) post;
            System.out.println("Post ID: " + feed.getPostId() +
                    ", Status: " + feed.getStatus() +
                    ", Type: " + feed.getPostType() +
                    ", Time Upload: " + feed.getTimeUpload() +
                    ", Likes: " + feed.getLikes());
        } else if (post instanceof Story) {
            Story story = (Story) post;
            System.out.println("Post ID: " + story.getPostId() +
                    ", Status: " + story.getStatus() +
                    ", Type: " + story.getPostType() +
                    ", Time Upload: " + story.getTimeUpload() +
                    ", Views: " + story.getView());
        }
    }
}
System.out.println("Number of deleted posts: " + deletedPostCount);


    public void changePostState(Post post, ContentState newStatus) {
        if (!list_post.contains(post)) {
            System.out.println("Post does not belong to this user.");
            return;
        }

        ContentState currentStatus = post.getStatus();
        ContentType postType = post.getPostType();

        if (currentStatus == newStatus) {
            System.out.println("Post is already in the desired state.");
            return;
        }

        switch (currentStatus) {
            case SHOWED:
                if (newStatus == ContentState.ARCHIVED || newStatus == ContentState.PINNED) {
                    post.setStatus(newStatus);
                    System.out.println("Post state changed to " + newStatus);
                }
                break;
            case ARCHIVED:
                if (newStatus == ContentState.DELETED) {
                    post.setStatus(newStatus);
                    System.out.println("Post state changed to " + newStatus);
                }
                break;
            case PINNED:
                if (postType == ContentType.PICTURE && newStatus == ContentState.SHOWED) {
                    int pinnedCount = countPinnedFeeds();
                    if (pinnedCount < 3) {
                        post.setStatus(newStatus);
                        System.out.println("Post state changed to " + newStatus);
                    } else {
                        System.out.println("You already have 3 pinned posts.");
                    }
                }
                break;
            default:
                System.out.println("Invalid state change.");
        }
    }

    private int countPinnedFeeds() {
    int pinnedCount = 0;
    for (int i = 0; i < list_post.size(); i++) {
        Post post = list_post.get(i);
        if (post.getPostType() == ContentType.PICTURE && post.getStatus() == ContentState.PINNED) {
            pinnedCount++;
        }
    }
    return pinnedCount;
    }

    public User(String userID, String username, String password, String bio, ArrayList<Post> list_post) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.list_post = list_post;
    }
}
