package ru.noties.tumbleweed.tutorial.scene;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import ru.noties.tumbleweed.Timeline;
import ru.noties.tumbleweed.Tween;
import ru.noties.tumbleweed.TweenDef;
import ru.noties.tumbleweed.TweenType;
import ru.noties.tumbleweed.equations.Back;
import ru.noties.tumbleweed.equations.Bounce;
import ru.noties.tumbleweed.equations.Elastic;
import ru.noties.tumbleweed.equations.Linear;

public class AnimationScene_Text2 extends AnimationScene {

    public AnimationScene_Text2() {
        super("Text with easing");
    }

    @Override
    public void animate(
            @NonNull View group,
            @NonNull View view1,
            @NonNull View view2,
            @NonNull View view3,
            @NonNull View view4) {

        final TextView text1 = (TextView) view1;
        final TextView text2 = (TextView) view2;
        final TextView text3 = (TextView) view3;
        final TextView text4 = (TextView) view4;

        Timeline.createSequence()
                .push(text(text1, 10).ease(Bounce.IN))
                .push(text(text2, 20).ease(Elastic.OUT))
                .push(text(text3, 30).ease(Back.IN))
                .push(text(text4, 40).ease(Linear.INOUT))
                .repeatYoyo(1, 1.F)
                .start(tweenManager(group));
    }

    @NonNull
    @Override
    public String description() {
        return "Interpolates text values with easing equations";
    }

    @NonNull
    private static TweenDef<TextView> text(@NonNull TextView textView, int value) {
        return Tween.to(textView, TextType.I, 1.F).target(value);
    }

    private static class TextType implements TweenType<TextView> {

        private static final TextType I = new TextType();

        @Override
        public int getValuesSize() {
            return 1;
        }

        @Override
        public void getValues(@NonNull TextView textView, @NonNull float[] values) {
            values[0] = Float.parseFloat(textView.getText().toString());
        }

        @Override
        public void setValues(@NonNull TextView textView, @NonNull float[] values) {
            final int value = (int) (values[0] + .5F);
            textView.setText(String.valueOf(value));
        }
    }
}
