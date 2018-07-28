package ru.noties.tumbleweed.android;

import android.support.annotation.NonNull;
import android.view.View;

// this should not be nessesary with the next release of tumbleweed
public class ViewTweenManagerExposedConstructor extends ViewTweenManager {

    public ViewTweenManagerExposedConstructor(int key, @NonNull View container) {
        super(key, container);
    }
}
