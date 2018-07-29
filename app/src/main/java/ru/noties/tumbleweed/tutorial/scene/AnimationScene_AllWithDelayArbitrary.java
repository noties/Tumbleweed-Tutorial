package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.TimelineDef;

public class AnimationScene_AllWithDelayArbitrary extends AnimationScene {

    public AnimationScene_AllWithDelayArbitrary() {
        super("All with delay arbitrary");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        setAlpha(.0F, view1, view2, view3, view4);

        final Delay delay = new Delay(.25F);

        final View[] views = {view1, view2, view3, view4};

        final TimelineDef def = Timeline.createParallel();
        for (View view : views) {
            def.push(fadeIn(view).delay(delay.next()));
        }

        def.start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Fade in views sequentially applying increasing delay for each (various views)";
    }

    static class Delay {

        private final float step;

        private float value;

        Delay(float step) {
            this.step = step;
            this.value = -step;
        }

        public float next() {
            return value += step;
        }
    }
}
