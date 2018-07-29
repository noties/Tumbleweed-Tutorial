package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;

public class AnimationScene_RepeatIndividualYoYo extends AnimationScene {

    public AnimationScene_RepeatIndividualYoYo() {
        super("Repeat individual yoyo");
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
                .push(fadeIn(view1).repeatYoyo(2, .25F))
                .push(fadeIn(view2).repeatYoyo(2, .5F))
                .push(fadeIn(view3).repeatYoyo(2, .75F))
                .push(fadeIn(view4).repeatYoyo(2, 1.F))
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Fade in views with individual repeat yoyo";
    }
}
