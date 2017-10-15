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

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of {@link android.webkit.WebResourceRequest} class for all API levels
 */
@SuppressWarnings("WeakerAccess")
public class ProWebResourceRequest {

    private String method;
    private Map<String, String> requestHeaders;
    private Uri uri;
    private boolean gesture;
    private boolean forMainFrame;
    private boolean redirect;

    ProWebResourceRequest(String method) {
        this(method, null);
    }

    ProWebResourceRequest(String method, Map<String, String> requestHeaders) {
        this(method, requestHeaders, null);
    }

    ProWebResourceRequest(String method, Map<String, String> requestHeaders, Uri uri) {
        this(method, requestHeaders, uri, false);
    }

    ProWebResourceRequest(String method, Map<String, String> requestHeaders, Uri uri, boolean gesture) {
        this(method, requestHeaders, uri, gesture, false);
    }

    ProWebResourceRequest(String method, Map<String, String> requestHeaders, Uri uri, boolean gesture, boolean forMainFrame) {
        this(method, requestHeaders, uri, gesture, forMainFrame, false);
    }

    ProWebResourceRequest(String method, Map<String, String> requestHeaders, Uri uri, boolean gesture, boolean forMainFrame, boolean redirect) {
        this.method = method;
        this.requestHeaders = requestHeaders;
        this.uri = uri;
        this.gesture = gesture;
        this.forMainFrame = forMainFrame;
        this.redirect = redirect;
    }

    /**
     * Gets the method associated with the request, for example "GET".
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gets the headers associated with the request.
     */
    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * Gets the URL for which the resource request was made.
     */
    public Uri getUrl() {
        return uri;
    }

    /**
     * Gets whether a gesture (such as a click) was associated with the request
     */
    public boolean hasGesture() {
        return gesture;
    }

    /**
     * Gets whether the request was made for the main frame
     */
    public boolean isForMainFrame() {
        return forMainFrame;
    }

    /**
     * Gets whether the request was a result of a server-side redirect
     */
    public boolean isRedirect() {
        return redirect;
    }

    static ProWebResourceRequest compatBuilder(String url) {
        return new ProWebResourceRequest("UNKNOWN", new HashMap<String, String>(), Uri.parse(url), false, false, false);
    }
}
