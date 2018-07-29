package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;

public class AnimationScene_AllWithDelay extends AnimationScene {

    public AnimationScene_AllWithDelay() {
        super("All with delay");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        setAlpha(.0F, view1, view2, view3, view4);

        Timeline.createParallel()
                .push(fadeIn(view1))
                .push(fadeIn(view2).delay(.25F))
                .push(fadeIn(view3).delay(.5F))
                .push(fadeIn(view4).delay(.75F))
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Fade in views sequentially applying increasing delay for each";
    }
}
