package ru.noties.tumbleweed.tutorial.scene;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.types.Translation;
import ru.noties.tumbleweed.android.utils.ViewUtils;
import ru.noties.tumbleweed.equations.Bounce;

public class AnimationScene_Expand2 extends AnimationScene {

    public AnimationScene_Expand2() {
        super("Expand2");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        ViewUtils.whenReady(group, view -> {

            placeViewInCenterOf(group, view1);
            placeViewInCenterOf(group, view2);
            placeViewInCenterOf(group, view3);
            placeViewInCenterOf(group, view4);

            Timeline.createParallel()
                    .push(translate(view1))
                    .push(translate(view2))
                    .push(translate(view3))
                    .push(translate(view4))
                    .repeatYoyo(2, 2.F)
                    .start(tweenManager(group));
        });
    }

    @NonNull
    @Override
    public String description() {
        return "All positioned at the center of parent and " +
                "translate to original position with easing";
    }

    private static void placeViewInCenterOf(@NonNull View parent, @NonNull View view) {

        final int centerX = (parent.getRight() + parent.getLeft()) / 2;
        final int centerY = (parent.getBottom() + parent.getTop()) / 2;

        final Point point = ViewUtils.relativeTo(parent, view);

        final int expectedX = centerX - (view.getWidth() / 2);
        final int expectedY = centerY - (view.getHeight() / 2);

        view.setTranslationX(expectedX - point.x);
        view.setTranslationY(expectedY - point.y);
    }

    @NonNull
    private static TweenDef<View> translate(@NonNull View view) {
        return Tween.to(view, Translation.XY, 1.F).target(.0F, .0F).ease(Bounce.OUT);
    }
}
