package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.types.Elevation;

public class AnimationScene_Elevation extends AnimationScene {

    public AnimationScene_Elevation() {
        super("Elevation");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        final float elevation = 16.F * group.getResources().getDisplayMetrics().density;

        Timeline.createSequence()
                .push(elevate(view1, elevation).repeatYoyo(1, .0F))
                .push(elevate(view2, elevation).repeatYoyo(1, .0F))
                .push(elevate(view3, elevation).repeatYoyo(1, .0F))
                .push(elevate(view4, elevation).repeatYoyo(1, .0F))
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Elevates views";
    }

    @NonNull
    private static TweenDef<View> elevate(@NonNull View view, float value) {
        return Tween.to(view, Elevation.I, 1.F).target(value);
    }
}
