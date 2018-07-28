package ru.noties.tumbleweed.tutorial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.noties.tumbleweed.TweenManager;
import ru.noties.tumbleweed.android.ViewTweenManagerExposedConstructor;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene;

public class SceneView extends LinearLayout {

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

        group = findViewById(R.id.group);

        view1 = findViewById(R.id.view_1);
        view2 = findViewById(R.id.view_2);
        view3 = findViewById(R.id.view_3);
        view4 = findViewById(R.id.view_4);

        play = findViewById(R.id.play);

        description = findViewById(R.id.description);
        source = findViewById(R.id.source);

        // a simple hack to cache our own instance of TweenManager to show/hide play icon
        tweenManager = new SceneViewTweenManager(R.id.tween_manager, group);
    }

    public void setScene(@NonNull AnimationScene scene) {

        if (this.scene != null) {
            throw new RuntimeException("Please do not re-use SceneView and set AnimationScene only once");
        }
        this.scene = scene;

        description.setText(scene.description());

        play.setOnClickListener(v -> {
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
    }

    @NonNull
    private String obtainSource(@NonNull AnimationScene scene) {
        return null;
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
                play.setVisibility(GONE);
            }
        }

        @Override
        protected void onStopped() {
            super.onStopped();

            if (!isDisposed()) {
                // show icon
                play.setVisibility(VISIBLE);
            }
        }
    }
}
