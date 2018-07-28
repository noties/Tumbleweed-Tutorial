package ru.noties.tumbleweed.tutorial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenManager;
import ru.noties.tumbleweed.android.ViewTweenManager;
import ru.noties.tumbleweed.android.ViewTweenManagerExposedConstructor;
import ru.noties.tumbleweed.android.types.Alpha;
import ru.noties.tumbleweed.equations.Cubic;
import ru.noties.tumbleweed.equations.Linear;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene;

public class SceneView extends LinearLayout {

    private View container;

    private View group;

    private View view1;
    private View view2;
    private View view3;
    private View view4;

    private View play;

    private TextView description;
    private TextView source;

    private TweenManager tweenManager;

    private AnimationScene scene;

    public SceneView(Context context) {
        super(context);
        init(context, null);
    }

    public SceneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        setOrientation(VERTICAL);
        setClipChildren(false);
        setClipToPadding(false);

        inflate(context, R.layout.view_scene, this);

        container = findViewById(R.id.container);

        group = container.findViewById(R.id.group);

        view1 = container.findViewById(R.id.view_1);
        view2 = container.findViewById(R.id.view_2);
        view3 = container.findViewById(R.id.view_3);
        view4 = container.findViewById(R.id.view_4);

        play = container.findViewById(R.id.play);

        description = findViewById(R.id.description);
        source = findViewById(R.id.source);

        // a simple hack to cache our own instance of TweenManager to show/hide play icon
        tweenManager = new SceneViewTweenManager(R.id.tween_manager, group);
    }

    public void setScene(@NonNull AnimationScene scene, @NonNull SourceCodeObtainer sourceCodeObtainer) {

        if (this.scene != null) {
            throw new RuntimeException("Please do not re-use SceneView and set AnimationScene only once");
        }
        this.scene = scene;

        description.setText(scene.description());

        container.setOnClickListener(v -> {
            if (tweenManager.isRunning()) {
                tweenManager.pause();
            } else {
                if (tweenManager.isPaused()) {
                    tweenManager.resume();
                } else {
                    scene.animate(group, view1, view2, view3, view4);
                }
            }
        });

        source.setText(sourceCodeObtainer.sourceCode(scene));
    }

    private class SceneViewTweenManager extends ViewTweenManagerExposedConstructor {

        public SceneViewTweenManager(int key, @NonNull View container) {
            super(key, container);
        }

        @Override
        protected void onStarted() {
            super.onStarted();

            if (!isDisposed()) {
                // hide play icon
                setPlayVisible(false);
            }
        }

        @Override
        protected void onStopped() {
            super.onStopped();

            if (!isDisposed()) {
                // show icon
                setPlayVisible(true);
            }
        }

        private void setPlayVisible(boolean visible) {
            Tween.to(play, Alpha.VIEW, .25F)
                    .target(visible ? 1.F : .0F)
                    .ease(visible ? Linear.INOUT : Cubic.IN)
                    .start(ViewTweenManager.get(R.id.tween_manager, play));
        }
    }
}
