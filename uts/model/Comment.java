package uts.model;

public class Comment {
    private String commentId;
    private String text;
    private ContentState status;

    public Comment(String commentId, String text, ContentState status) {
        this.commentId = commentId;
        this.text = text;
        this.status = status;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ContentState getStatus() {
        return status;
    }

    public void setStatus(ContentState status) {
        this.status = status;
    }

}