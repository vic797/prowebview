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

/**
 * An implementation of {@link android.webkit.WebResourceError} class for all API levels
 */

public class ProWebResourceError {

    private CharSequence description;
    private int errorCode;

    ProWebResourceError(CharSequence description, int errorCode) {
        this.description = description;
        this.errorCode = errorCode;
    }

    /**
     * Get the description of the error
     */
    public CharSequence getDescription() {
        return description;
    }

    /**
     * Get the error code
     */
    public int getErrorCode() {
        return errorCode;
    }
}
