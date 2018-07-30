package ru.noties.tumbleweed.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import ru.noties.tumbleweed.tutorial.scene.AnimationScene;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_AllWithDelay;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_AllWithDelay2;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_AllWithDelay3;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_AllWithDelay4;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_AllWithDelayArbitrary;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Basic;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Basic2;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Color;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Elevation;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Expand;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Expand2;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_ExpandSequential;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Grouped;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Parallel;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Parallel2;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Repeat;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_RepeatIndividual;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_RepeatIndividualYoYo;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_RepeatYoYo;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Rotation;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Rotation2;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Scale;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Text;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Text2;

public class MainActivity extends Activity {

    private static final String KEY_SELECTED_SCENE = "key.SelectedScene";

    private ViewGroup container;
    private SourceCodeObtainer sourceCodeObtainer;
    private ScenePickerView scenePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int selected = savedInstanceState != null
                ? savedInstanceState.getInt(KEY_SELECTED_SCENE, 0)
                : 0;

        container = findViewById(R.id.scroll_view);
        sourceCodeObtainer = SourceCodeObtainer.create(this);

        scenePickerView = findViewById(R.id.scene_picker);
        scenePickerView.setScenes(selected, animationScenes(), this::showScene);
    }

    private void showScene(@NonNull AnimationScene scene) {

        container.removeAllViews();

        final SceneView view = new SceneView(this);
        view.setScene(scene, sourceCodeObtainer);
        container.addView(view);
        container.post(() -> container.scrollTo(0, 0));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (scenePickerView != null) {
            outState.putInt(KEY_SELECTED_SCENE, scenePickerView.getSelected());
        }
    }

    @NonNull
    private static List<AnimationScene> animationScenes() {
        return Arrays.asList(
                new AnimationScene_Basic(),
                new AnimationScene_Basic2(),
                new AnimationScene_Parallel(),
                new AnimationScene_Parallel2(),
                new AnimationScene_Grouped(),
                new AnimationScene_AllWithDelay(),
                new AnimationScene_AllWithDelayArbitrary(),
                new AnimationScene_AllWithDelay2(),
                new AnimationScene_AllWithDelay3(),
                new AnimationScene_AllWithDelay4(),
                new AnimationScene_Repeat(),
                new AnimationScene_RepeatYoYo(),
                new AnimationScene_RepeatIndividual(),
                new AnimationScene_RepeatIndividualYoYo(),
                new AnimationScene_Expand(),
                new AnimationScene_Expand2(),
                new AnimationScene_ExpandSequential(),
                new AnimationScene_Color(),
                new AnimationScene_Text(),
                new AnimationScene_Text2(),
                new AnimationScene_Rotation(),
                new AnimationScene_Rotation2(),
                new AnimationScene_Scale(),
                new AnimationScene_Elevation()
        );
    }
}
