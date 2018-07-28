package ru.noties.tumbleweed.tutorial;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import ru.noties.tumbleweed.android.utils.ViewUtils;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene_Basic;

public class MainActivity extends Activity {

    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.scroll_view);

        showScene(new AnimationScene_Basic());
    }

    private void showScene(@NonNull AnimationScene scene) {
        container.removeAllViews();

        final SceneView view = new SceneView(this);
        view.setScene(scene);
        container.addView(view);
    }

//    private void temp() {
//        viewTweenManager.killAll();
//
//        view1.setAlpha(.0F);
//        view2.setAlpha(.0F);
//        view3.setAlpha(.0F);
//        view4.setAlpha(.0F);
//
////        Timeline.createSequence()
////                .push(fadeIn(view1))
////                .push(fadeIn(view2))
////                .push(fadeIn(view3))
////                .push(fadeIn(view4))
////                .start(ViewTweenManager.get(group.getId(), group));
//
////        Timeline.createSequence()
////                .push(fadeIn(view1))
////                .push(fadeIn(view3))
////                .push(fadeIn(view4))
////                .push(fadeIn(view2))
////                .start(ViewTweenManager.get(group.getId(), group));
//
////        Timeline.createParallel()
////                .push(fadeIn(view1))
////                .push(fadeIn(button2, 2.F))
////                .push(fadeIn(button3))
////                .push(fadeIn(button4))
////                .start(ViewTweenManager.get(group.getId(), group));
//
////        Timeline.createSequence()
////                .push(Timeline.createParallel()
////                        .push(fadeIn(view1))
////                        .push(fadeIn(view2)))
////                .push(Timeline.createParallel()
////                        .push(fadeIn(view3))
////                        .push(fadeIn(view4)))
////                .start(ViewTweenManager.get(group.getId(), group));
//
////        Timeline.createParallel()
////                .push(fadeIn(view1))
////                .push(fadeIn(view2).delay(.1F))
////                .push(fadeIn(view3).delay(.2F))
////                .push(fadeIn(view4).delay(.3F))
////                .start(ViewTweenManager.get(group.getId(), group));
//
////        final View[] views = {view1, view2, view3, view4};
////        final Delay delay = new Delay(.1F);
////
////        final TimelineDef def = Timeline.createParallel();
////        for (View view: views) {
////            def.push(fadeIn(view).delay(delay.next()));
////        }
////        def.start(viewTweenManager(group));
//
////        Timeline.createParallel()
////                .push(fadeIn(view1))
////                .push(fadeIn(view2).delay(.1F).ease(Back.IN))
////                .push(fadeIn(view3).delay(.2F).ease(Elastic.OUT))
////                .push(fadeIn(view4).delay(.3F).ease(Bounce.INOUT))
////                .start(ViewTweenManager.get(group.getId(), group));
//
////        Timeline.createParallel()
////                .push(fadeIn(view1))
////                .push(fadeIn(view2).delay(.1F).ease(Back.IN))
////                .push(fadeIn(view3).delay(.2F).ease(Elastic.OUT))
////                .push(fadeIn(view4).delay(.3F).ease(Bounce.INOUT))
////                .repeatYoyo(1, 1.F)
////                .start(viewTweenManager(group));
//
//        // NB unfortunately we cannot push infinite tweens into a timeline (runtime exception will be thrown)
//        // but infinite timelines are possible
////        Timeline.createParallel()
////                .push(fadeIn(view1).repeatYoyo(2, .0F))
////                .push(fadeIn(view3).delay(.5F).repeatYoyo(2, .0F))
////                .push(fadeIn(view4).delay(1.F).repeatYoyo(2, .0F))
////                .push(fadeIn(view2).delay(1.5F).repeatYoyo(2, .0F))
////                .start(viewTweenManager);
//
//        // first is visible, others expand from it
//        //
//        // we will use this utility method to wait when view will be ready to be drawn (measured and layed out)
//        // see OnPreDrawListener
//        ViewUtils.whenReady(group, view -> {
//
////            final int centerX = (view.getRight() + view.getLeft()) / 2;
////            final int centerY = (view.getBottom() + view.getTop()) / 2;
////            final Point center = new Point(
////                    (view.getRight() + view.getLeft()) / 2,
////                    (view.getBottom() + view.getTop()) / 2
////            );
////
////            final Point point1 = ViewUtils.relativeTo(group, view1);point1.offset(
////                    -(centerX - (view1.getWidth() / 2)),
////                    -(centerY - (view1.getHeight() / 2))
////            );
////            final Point point2 = ViewUtils.relativeTo(group, view2);
////            final Point point3 = ViewUtils.relativeTo(group, view3);
////            final Point point4 = ViewUtils.relativeTo(group, view4);
////
//////            Debug.i("center{x: %d, y: %d}, point1: %s, point2: %s, point3: %s, point4: %s", centerX, centerY, point1, point2, point3, point4);
////
////            // center{x: 384, y: 512}, point1: Point(312, 315), point2: Point(196, 440), point3: Point(428, 440), point4: Point(312, 565)
////            // view{w: 144, h: 144}
////            final int expectedX = centerX - (view1.getWidth() / 2);
////            final int translationX = point1.x - expectedX;
////            Debug.i("expectedX: %d, translationX: %d, view{w: %d, h; %d}", expectedX, translationX, view1.getWidth(), view1.getHeight());
//
//            placeViewInCenterOf(group, view1);
//            placeViewInCenterOf(group, view2);
//            placeViewInCenterOf(group, view3);
//            placeViewInCenterOf(group, view4);
//
//            view1.setAlpha(1.F);
//            view2.setAlpha(1.F);
//            view3.setAlpha(1.F);
//            view4.setAlpha(1.F);
//
//            Timeline.createParallel()
//                    .push(translateIn(view1))
//                    .push(translateIn(view2))
//                    .push(translateIn(view3))
//                    .push(translateIn(view4))
//                    .repeatYoyo(3, 1.F)
//                    .start(viewTweenManager);
//        });
//    }

    private static void placeViewInCenterOf(@NonNull View parent, @NonNull View view) {

        final int centerX = (parent.getRight() + parent.getLeft()) / 2;
        final int centerY = (parent.getBottom() + parent.getTop()) / 2;

        final Point point = ViewUtils.relativeTo(parent, view);

        final int expectedX = centerX - (view.getWidth() / 2);
        final int expectedY = centerY - (view.getHeight() / 2);

        view.setTranslationX(expectedX - point.x);
        view.setTranslationY(expectedY - point.y);
    }

    public static class Delay {

        private final float step;

        private float value;

        public Delay(float step) {
            this.step = step;
            this.value = -step;
        }

        public float next() {
            return value += step;
        }
    }
}
