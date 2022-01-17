package org.coffee.diary.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.coffee.diary.javafx.catalog.CalalogMouseMenu;
import org.coffee.diary.javafx.catalog.CatalogTreeCell;
import org.coffee.diary.javafx.catalog.CatalogTreeItem;
import org.coffee.diary.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;

@FXMLController
public class IndexController implements Initializable {

	private final Logger		log	= LoggerFactory.getLogger(getClass());

	@Value("classpath:/diary/img/loading.jpeg")
	private Resource			leisureImg;

	@FXML
	private Menu				fileMenu;
	@FXML
	private TreeView<String>	catalogTree;
	@FXML
	private AnchorPane			splitRightAnchorPane;
	@FXML
	private HTMLEditor			note;
	@FXML
	private TableView<String>	noteList;

	@Autowired
	private NoteService			noteService;

	@FXML
	private void saveAction(ActionEvent event) {

		System.out.println("saveAction");
	}

	@FXML
	private void exitAction(ActionEvent event) {

		Platform.exit();
		System.exit(0);
	}

	@FXML
	private void aboutAction(ActionEvent event) throws Exception {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("关于");
		alert.setContentText("没有比这更简单的叻！");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	@FXML
	private void onMouseClicked(MouseEvent event) {

		System.out.println("onMouseClicked");
	}

	@FXML
	private void onKeyPressed(KeyEvent event) {

		if (KeyCode.DOWN.equals(event.getCode())
				|| KeyCode.UP.equals(event.getCode())) {
			System.out.println(event.getCode());
			System.out.println("onKeyPressed");
		}
		if (KeyCode.LEFT.equals(event.getCode())
				|| KeyCode.RIGHT.equals(event.getCode())) {
			System.out.println(event.getCode());
			System.out.println("onKeyPressed");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initBorderPaneLeft();
		initBorderPaneCenter();
	}

	private void initBorderPaneCenter() {

		HTMLEditor node2 = new HTMLEditor();

		TableView<String> node = new TableView<String>();
		node.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		node.getColumns().add(0, new TableColumn<String, String>("标题"));
		node.getColumns().add(1, new TableColumn<String, String>("内容摘要"));
		node.getColumns().add(2, new TableColumn<String, String>("修改时间"));
		AnchorPane.setLeftAnchor(node, 2.0);
		AnchorPane.setBottomAnchor(node, 2.0);
		AnchorPane.setRightAnchor(node, 2.0);
		AnchorPane.setTopAnchor(node, 2.0);
		// splitRightAnchorPane.getChildren().add(node);

	}

	private void initBorderPaneLeft() {

		CatalogTreeItem catalog = noteService.readCatalog();
		if (catalog != null) {
			catalogTree.setRoot(catalog);
			// 是否显示根目录
			catalogTree.setShowRoot(true);
			catalogTree.setEditable(true);
			catalogTree.setCellFactory(v -> new CatalogTreeCell(noteService));
		}
		CalalogMouseMenu mouseMenu = new CalalogMouseMenu(noteService, catalogTree);
		mouseMenu.getInstance();
		catalogTree.setContextMenu(mouseMenu);
	}
}
