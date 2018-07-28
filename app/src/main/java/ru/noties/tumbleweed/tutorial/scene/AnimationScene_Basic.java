package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.android.ViewTweenManager;
import ru.noties.tumbleweed.android.types.Alpha;
import ru.noties.tumbleweed.tutorial.R;

public class AnimationScene_Basic extends AnimationScene {

    public AnimationScene_Basic() {
        super("Basic");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        view1.setAlpha(.0F);
        view2.setAlpha(.0F);
        view3.setAlpha(.0F);
        view4.setAlpha(.0F);

        Timeline.createSequence()
                .push(Tween.to(view1, Alpha.VIEW, 1.F).target(1.F))
                .push(Tween.to(view2, Alpha.VIEW, 1.F).target(1.F))
                .push(Tween.to(view3, Alpha.VIEW, 1.F).target(1.F))
                .push(Tween.to(view4, Alpha.VIEW, 1.F).target(1.F))
                .start(ViewTweenManager.get(R.id.tween_manager, group));
    }

    @NonNull
    @Override
    public String description() {
        return "First scene that fades in view in a sequence (and does not use helper methods)";
    }
}
