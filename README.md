# RecyclerViewItemDecoration

[![Release](https://jitpack.io/v/phongbm/RecyclerViewItemDecoration.svg)](https://jitpack.io/#phongbm/RecyclerViewItemDecoration)
A set of simple ItemDecoration for Android RecyclerView.

## Features

- Drawing spacing between items.
- Drawing divider between items.

##### 1. Spacing ItemDecoration for LinearLayoutManager

- Support both orientation: vertical and horizontal.
- Support to draw 4 styles: both, center, start, end.
- With or without around edge.

##### 2. Spacing ItemDecoration for GridLayoutManager

- With or without around edge.

##### 3. Divider ItemDecoration for LinearLayout

- Currently, only support for vertical LinearLayoutManager.
- Support to draw 4 styles: both, center, start, end.
- Support marginStart, marginEnd.

## Installation

Add to your project's root `build.gradle` file:

```sh
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency in your `app/build.gradle` file:

```sh
dependencies {
    implementation 'com.github.phongbm:RecyclerViewItemDecoration:$version'
}
```

Replace the variable `$version` with the latest
version: [![Release](https://jitpack.io/v/phongbm/RecyclerViewItemDecoration.svg)](https://jitpack.io/#phongbm/RecyclerViewItemDecoration)
