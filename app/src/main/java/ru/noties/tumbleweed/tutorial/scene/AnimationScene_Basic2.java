package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;

public class AnimationScene_Basic2 extends AnimationScene {

    public AnimationScene_Basic2() {
        super("Basic2");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        setAlpha(.0F, view1, view2, view3, view4);

        Timeline.createSequence()
                .push(fadeIn(view1))
                .push(fadeIn(view2))
                .push(fadeIn(view3))
                .push(fadeIn(view4))
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "First scene that fades in view in a sequence";
    }
}
