/*
Copyright 2017 Victor Campos

Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


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
