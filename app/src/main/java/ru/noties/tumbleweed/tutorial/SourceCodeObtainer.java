package ru.noties.tumbleweed.tutorial;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import ru.noties.markwon.Markwon;
import ru.noties.markwon.SpannableConfiguration;
import ru.noties.markwon.spans.SpannableTheme;
import ru.noties.markwon.syntax.Prism4jSyntaxHighlight;
import ru.noties.markwon.syntax.Prism4jThemeDefault;
import ru.noties.prism4j.Prism4j;
import ru.noties.prism4j.annotations.PrismBundle;
import ru.noties.tumbleweed.tutorial.scene.AnimationScene;

@PrismBundle(include = "java")
public abstract class SourceCodeObtainer {

    @NonNull
    public abstract CharSequence sourceCode(@NonNull AnimationScene scene);

    @NonNull
    public static SourceCodeObtainer create(@NonNull Context context) {

        final Prism4j prism4j = new Prism4j(new GrammarLocatorDef());
        final SpannableTheme theme = SpannableTheme.builderWithDefaults(context)
                .codeBackgroundColor(0x10000000)
                .build();

        final SpannableConfiguration configuration = SpannableConfiguration.builder(context)
                .theme(theme)
                .syntaxHighlight(Prism4jSyntaxHighlight.create(prism4j, Prism4jThemeDefault.create()))
                .build();

        return new Impl(context, configuration);
    }

    static class Impl extends SourceCodeObtainer {

        private final Context context;
        private final SpannableConfiguration configuration;

        Impl(@NonNull Context context, @NonNull SpannableConfiguration configuration) {
            this.context = context;
            this.configuration = configuration;
        }

        @NonNull
        @Override
        public CharSequence sourceCode(@NonNull AnimationScene scene) {
            return Markwon.markdown(configuration, markdown(scene));
        }

        @NonNull
        private String markdown(@NonNull AnimationScene scene) {
            return "```java\n" + source(scene) + "\n```";
        }

        @NonNull
        private String source(@NonNull AnimationScene scene) {

            final String name = "scene/" + scene.getClass().getSimpleName() + ".java";

            try (InputStream inputStream = context.getAssets().open(name)) {
                final Scanner scanner = new Scanner(inputStream, "UTF-8")
                        .useDelimiter("\\A");
                if (scanner.hasNext()) {
                    return scanner.next();
                } else {
                    throw new IOException("Cannot find source file for the scene: " + name + "\n" +
                            "or it is empty");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
