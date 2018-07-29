package ru.noties.tumbleweed.tutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.android.ViewTweenManager;
import ru.noties.tumbleweed.android.types.Alpha;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene;

public class ScenePickerView extends LinearLayout {

    public interface SceneSelectedListener {
        void onSceneSelected(@NonNull AnimationScene scene);
    }

    private View group;
    private TextView name;

    private View previous;
    private View next;

    private int selected;
    private List<AnimationScene> scenes;
    private SceneSelectedListener listener;

    public ScenePickerView(Context context) {
        super(context);
        init(context, null);
    }

    public ScenePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        setOrientation(HORIZONTAL);

        inflate(context, R.layout.view_scene_picker, this);

        group = findViewById(R.id.group);
        name = group.findViewById(R.id.name);

        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
    }

    public void setScenes(int selected, @NonNull List<AnimationScene> scenes, @NonNull SceneSelectedListener listener) {
        this.selected = selected;
        this.scenes = scenes;
        this.listener = listener;

        select(selected);

        group.setOnClickListener(v -> openPicker());
    }

    public int getSelected() {
        return selected;
    }

    private void select(int index) {

        this.selected = index;

        final AnimationScene scene = scenes.get(index);

        name.setText(sceneName(index, scene.name()));
        name.post(() -> name.requestLayout());

        // if greater than zero than always have _back_ option
        if (selected > 0) {
            previous.setEnabled(true);
            previous.setOnClickListener(v -> select(selected - 1));
            enableMenuItem(previous, true);
        } else {
            previous.setEnabled(false);
            previous.setOnClickListener(null);
            enableMenuItem(previous, false);
        }

        if (selected < scenes.size() - 1) {
            next.setEnabled(true);
            next.setOnClickListener(v -> select(selected + 1));
            enableMenuItem(next, true);
        } else {
            next.setEnabled(false);
            next.setOnClickListener(null);
            enableMenuItem(next, false);
        }

        listener.onSceneSelected(scene);
    }

    private void openPicker() {

        final List<AnimationScene> scenes = this.scenes;
        final int length = scenes.size();
        final CharSequence[] items = new CharSequence[length];
        for (int i = 0; i < length; i++) {
            items[i] = sceneName(i, scenes.get(i).name());
        }

        new AlertDialog.Builder(getContext())
                .setSingleChoiceItems(items, selected, (dialog, which) -> {
                    if (selected != which) {
                        select(which);
                    }
                    dialog.dismiss();
                })
                .show();
    }

    @NonNull
    private static String sceneName(int index, @NonNull String name) {
        return "#" + index + ": " + name;
    }

    private void enableMenuItem(@NonNull View view, boolean enable) {
        final float alpha = enable
                ? 1.F
                : .1F;
        if (Float.compare(alpha, view.getAlpha()) != 0) {
            Tween.to(view, Alpha.VIEW, .25F)
                    .target(alpha)
                    .start(ViewTweenManager.get(R.id.tween_manager, view));
        }
    }
}
