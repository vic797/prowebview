package vcm.github.webkit.proview;

class PendingDownload {

    private String url, userAgent, content, mime;
    private long len;


    PendingDownload(String url, String userAgent, String content, String mime, long len) {
        this.url = url;
        this.userAgent = userAgent;
        this.content = content;
        this.mime = mime;
        this.len = len;
    }

    String getUrl() {
        return url;
    }

    String getUserAgent() {
        return userAgent;
    }

    String getContent() {
        return content;
    }

    String getMime() {
        return mime;
    }

    long getLen() {
        return len;
    }
}
