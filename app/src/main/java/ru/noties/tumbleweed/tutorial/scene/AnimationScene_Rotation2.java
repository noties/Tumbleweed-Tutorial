package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.types.Rotation;

public class AnimationScene_Rotation2 extends AnimationScene {

    public AnimationScene_Rotation2() {
        super("Rotation2");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        final float rotation = 360.F;

        Timeline.createParallel()
                .push(rotate(group, rotation))
                .push(rotate(view1, -rotation))
                .push(rotate(view2, -rotation))
                .push(rotate(view3, -rotation))
                .push(rotate(view4, -rotation))
                .repeatYoyo(1, 1.F)
                .start(tweenManager(group));

    }

    @NonNull
    @Override
    public String description() {
        return "Rotates views";
    }

    @NonNull
    private static TweenDef<View> rotate(@NonNull View view, float rotation) {
        return Tween.to(view, Rotation.I, 2.F).target(rotation);
    }
}
