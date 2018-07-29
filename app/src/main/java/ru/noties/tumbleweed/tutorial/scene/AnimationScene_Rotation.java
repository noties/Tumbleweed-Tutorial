package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.types.Rotation;

public class AnimationScene_Rotation extends AnimationScene {

    public AnimationScene_Rotation() {
        super("Rotation");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        Timeline.createSequence()
                .push(rotate(view1, 100.F))
                .push(rotate(view2, 200.F))
                .push(rotate(view3, 300.F))
                .push(rotate(view4, 400.F))
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
        return Tween.to(view, Rotation.I, 1.F).target(rotation);
    }
}
