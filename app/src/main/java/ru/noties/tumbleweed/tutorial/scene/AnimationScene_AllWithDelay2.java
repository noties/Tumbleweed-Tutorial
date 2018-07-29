package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.equations.Back;
import ru.noties.tumbleweed.equations.Bounce;
import ru.noties.tumbleweed.equations.Cubic;
import ru.noties.tumbleweed.equations.Elastic;
import ru.noties.tumbleweed.equations.Linear;

public class AnimationScene_AllWithDelay2 extends AnimationScene {

    public AnimationScene_AllWithDelay2() {
        super("All with delay (easing)");
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
                .push(fadeIn(view1, 2.F).ease(Linear.INOUT))
                .push(fadeIn(view2, 2.F).delay(.25F).ease(Back.IN))
                .push(fadeIn(view3, 2.F).delay(.5F).ease(Elastic.INOUT))
                .push(fadeIn(view4, 2.F).delay(.75F).ease(Bounce.OUT))
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Fade in views sequentially applying increasing delay for each " +
                "and showing usage of different easing equations";
    }
}
