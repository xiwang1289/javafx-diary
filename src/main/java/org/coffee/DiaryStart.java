package org.coffee;

import org.coffee.diary.view.IndexView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

@SpringBootApplication
public class DiaryStart extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {

		SplashScreen splashScreen = new SplashScreen() {

			@Override
			public String getImagePath() {

				return "/diary/img/loading.jpeg";
			}

			@Override
			public Parent getParent() {

				final ImageView imageView = new ImageView(getClass().getResource(getImagePath()).toExternalForm());
				final ProgressBar splashProgressBar = new ProgressBar();
				splashProgressBar.setPrefWidth(imageView.getImage().getWidth());
				final VBox vbox = new VBox();
				vbox.getChildren().addAll(imageView, splashProgressBar);

				return vbox;
			}
		};
		launch(DiaryStart.class, IndexView.class, splashScreen, args);
	}
}
