package ru.noties.tumbleweed.tutorial.scene;

import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.types.Argb;
import ru.noties.tumbleweed.tutorial.R;

public class AnimationScene_Color extends AnimationScene {

    public AnimationScene_Color() {
        super("Color");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        // we will change views to use simple background (square) for this scene

        final Resources r = group.getResources();
        final int color1 = r.getColor(R.color.color_1);
        final int color2 = r.getColor(R.color.color_2);
        final int color3 = r.getColor(R.color.color_3);
        final int color4 = r.getColor(R.color.color_4);

        view1.setBackgroundColor(color1);
        view2.setBackgroundColor(color2);
        view3.setBackgroundColor(color3);
        view4.setBackgroundColor(color4);

        Timeline.createSequence()
                .push(Timeline.createParallel()
                        .push(color(view1, color2))
                        .push(color(view2, color1)))
                .push(Timeline.createParallel()
                        .push(color(view3, color4))
                        .push(color(view4, color3)))
                .repeatYoyo(1, 1.F)
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Animates colors of views";
    }

    @NonNull
    private static TweenDef<View> color(@NonNull View view, @ColorInt int color) {
        return Tween.to(view, Argb.BACKGROUND, 1.F).target(Argb.toArray(color));
    }
}
