package uts.controller;
import uts.model.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    public static void dummy() {
        ArrayList<User> dummyUser = new ArrayList<>();
        ArrayList<Comment> dummyComment = new ArrayList<>();
        ArrayList<Feed> dummyFeeds = new ArrayList<>();
        ArrayList<Reel> dummyReels = new ArrayList<>();
        ArrayList<Story> dummyStory = new ArrayList<>();

        // Dummy user
        User user1 = new User("user1", "pingcen", "password1", "ingfokan", new ArrayList<>());
        User user2 = new User("user2", "eclex", "password2", "pinjam dulu seratus", new ArrayList<>());
        dummyUser.add(user1);
        dummyUser.add(user2);

        // Comment dummy
        Comment comment1 = new Comment("c1", "amazing", ContentState.SHOWED);
        Comment comment2 = new Comment("c2", "awesome", ContentState.SHOWED);
        dummyComment.add(comment1);
        dummyComment.add(comment2);
        //  feeds dummy
        Feed feed1 = new Feed("f1", ContentState.SHOWED, new Timestamp(System.currentTimeMillis()), ContentType.PICTURE, dummyComment, "apa aja", 10);
        Feed feed2 = new Feed("f2", ContentState.SHOWED, new Timestamp(System.currentTimeMillis()), ContentType.VIDEO, dummyComment, "apa aja 2", 20);
        dummyFeeds.add(feed1);
        dummyFeeds.add(feed2);

        // reels dummy
        Reel reel1 = new Reel("r1", ContentState.SHOWED, new Timestamp(System.currentTimeMillis()), ContentType.VIDEO, new ArrayList<>(Arrays.asList(comment2)), 100, 10);
        Reel reel2 = new Reel("r2", ContentState.SHOWED, new Timestamp(System.currentTimeMillis()), ContentType.VIDEO, new ArrayList<>(Arrays.asList(comment2)), 19, 890);
        dummyReels.add(reel1);
        dummyReels.add(reel2);

        // Story dummy
        Story story1 = new Story("s1", ContentState.SHOWED, new Timestamp(System.currentTimeMillis()), ContentType.PICTURE, new ArrayList<>(Arrays.asList(comment1, comment2)), 15, 100);
        Story story2 = new Story("s2", ContentState.SHOWED, new Timestamp(System.currentTimeMillis()), ContentType.PICTURE, new ArrayList<>(Arrays.asList(comment1, comment2)), 20, 100);
        dummyStory.add(story1);
        dummyStory.add(story2);

    }
    public String printData(ArrayList<Post> list_post, int j) {
        String hasil = "";
        if (list_post.get(j) instanceof Story) {
            Story story = (Story) list_post.get(j);
            return hasil = "\nViews: " + story.getView() + list_post.get(j).getStatus();
        } else if (list_post.get(j) instanceof Feed) {
            Feed feed = (Feed) list_post.get(j);
            return hasil = "\nUsername : " + feed.getLikes() + list_post.get(j).getStatus();
        } else if (list_post.get(j) instanceof Reel){
            Reel reel = (Reel) list_post.get(j);
            return hasil = "\nUsername : " + reel.getPlayCount() + list_post.get(j).getStatus();
        } else {
            return "";
        }
    }
    public String showUserPosts(ArrayList <User> listUser){
        String pinned = "";
        String showed = "";
        String archived = "";
        int jumlahDelete = 0;

        for (int i = 0; i < listUser.size(); i++) {
            ArrayList<Post> listpost = listUser.get(i).getList_post();
            for (int j = 0; j < listpost.size(); j++) {
                switch (listpost.get(j).getStatus()) {
                    case PINNED:
                        pinned += printData(listpost,j);
                        break;
                    case SHOWED:
                        showed += printData(listpost,j);
                        break;
                    case ARCHIVED:
                        archived += printData(listpost,j);
                        break;
                    case DELETED:
                        jumlahDelete++;
                    default:
                        break;
                }
            }
        }
        String hasil = pinned + showed + archived + "Jumlah post yg sudah di deleted : " + jumlahDelete;
        return hasil;
    }
    public void showPost(String id, User user) {
        List<Post> posts = user.getList_post();
        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);

            if (p instanceof Reel) {
                Reel reel = (Reel) p;
                if (reel.getPostId().equals(id)) {
                    System.out.println("Post ID: " + reel.getPostId() +
                            ", Status: " + reel.getStatus() +
                            ", Type: " + reel.getPostType() +
                            ", Time Upload: " + reel.getTimeUpload() +
                            ", Duration: " + reel.getDuration() +
                            ", Play Count: " + reel.getPlayCount());
                    return; // Exit the loop when the post is found
                }
            } else if (p instanceof Story) {
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
            } else if (p instanceof Feed) {
                Feed feed = (Feed) p;
                if (feed.getPostId().equals(id)) {
                    System.out.println("Post ID: " + feed.getPostId() +
                            ", Status: " + feed.getStatus() +
                            ", Type: " + feed.getPostType() +
                            ", Time Upload: " + feed.getTimeUpload() +
                            ", Caption: " + feed.getCaption() +
                            ", Likes: " + feed.getLikes());
                    return; // Exit the loop when the post is found
                }
            }
        }

        System.out.println("postingan tidak ditemukan");
    }
    



}

