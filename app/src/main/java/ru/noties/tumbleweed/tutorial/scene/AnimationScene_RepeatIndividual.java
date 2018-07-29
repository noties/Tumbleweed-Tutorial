package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;

public class AnimationScene_RepeatIndividual extends AnimationScene {

    public AnimationScene_RepeatIndividual() {
        super("Repeat individual");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        setAlpha(.0F, view1, view2, view3, view4);

        // NB unfortunately we cannot push infinite tweens into a timeline (runtime exception will be thrown)
        // but infinite timelines are possible
        Timeline.createParallel()
                .push(fadeIn(view1).repeat(2, .25F))
                .push(fadeIn(view2).repeat(2, .5F))
                .push(fadeIn(view3).repeat(2, .75F))
                .push(fadeIn(view4).repeat(2, 1.F))
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Fade in views with individual repeat";
    }
}
