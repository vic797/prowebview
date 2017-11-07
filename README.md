[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ProWebView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6331) [![](https://jitpack.io/v/vic797/prowebview.svg)](https://jitpack.io/#vic797/prowebview)

# ProWebView
ProWebView is another WebView for Android with more functionality and without the hell of the WebViewClient and the WebChromeClient. With ProWebView you don't have to worry about asking permissions, file download, file upload, JS alerts and dialogs, links without protocols, cache managing and/or cookies managing.

## Installation
First of all you need to add the jitpack.io repo to your top-level gradle file:
```gradle
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```

And add this dependence to the app-level gradle:

```gradle
dependencies {
    compile 'com.github.vic797:prowebview:VERSION'
}
```

## Usage
Please refer to the [wiki](https://github.com/vic797/prowebview/wiki) to know how to use it.

## Contribute
ProWebView has a lot of features and not all of that features have been tested exhaustively; please report any bug you find. Thanks!

## Licence

```
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
```
