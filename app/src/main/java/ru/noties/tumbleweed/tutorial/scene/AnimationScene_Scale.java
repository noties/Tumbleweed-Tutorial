package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.types.Scale;

public class AnimationScene_Scale extends AnimationScene {

    public AnimationScene_Scale() {
        super("Scale");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        setScale(view1, .0F);
        setScale(view2, .0F);
        setScale(view3, .0F);
        setScale(view4, .0F);

        Timeline.createSequence()
                .push(scale(view1, 1.F))
                .push(scale(view2, 1.F))
                .push(scale(view3, 1.F))
                .push(scale(view4, 1.F))
                .repeatYoyo(1, 1.F)
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Scales in views";
    }

    private static void setScale(@NonNull View view, float scale) {
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    @NonNull
    private static TweenDef<View> scale(@NonNull View view, float scale) {
        return Tween.to(view, Scale.XY, 1.F).target(scale, scale);
    }
}
