package org.coffee;

import org.coffee.diary.view.NoteView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
		launch(DiaryStart.class, NoteView.class, splashScreen, args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		super.start(stage);
		// 设置快捷键
		// getScene().getAccelerators().put();
	}
}
