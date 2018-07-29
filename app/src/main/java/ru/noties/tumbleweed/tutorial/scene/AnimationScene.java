package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.view.View;

import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.android.ViewTweenManager;
import ru.noties.tumbleweed.android.types.Alpha;
import ru.noties.tumbleweed.tutorial.R;

@SuppressWarnings("WeakerAccess")
public abstract class AnimationScene {

    private final String name;

    public AnimationScene(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String name() {
        return name;
    }

    public abstract void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4
    );

    @NonNull
    public abstract String description();

    @NonNull
    protected static ViewTweenManager tweenManager(@NonNull View view) {
        final ViewTweenManager manager = ViewTweenManager.get(R.id.tween_manager, view);
        manager.killAll();
        return manager;
    }

    @NonNull
    protected static TweenDef<View> fadeIn(@NonNull View view) {
        return fadeIn(view, 1.F);
    }

    @NonNull
    protected static TweenDef<View> fadeIn(@NonNull View view, float duration) {
        return Tween.to(view, Alpha.VIEW, duration).target(1.F);
    }

    protected static void setAlpha(
            @FloatRange(from = .0F, to = 1.F) float alpha,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4
    ) {
        view1.setAlpha(alpha);
        view2.setAlpha(alpha);
        view3.setAlpha(alpha);
        view4.setAlpha(alpha);
    }

//    protected static void setAlpha(@FloatRange(from = .0F, to = 1.F) float alpha, View... views) {
//        for (View view : views) {
//            view.setAlpha(alpha);
//        }
//    }
}
