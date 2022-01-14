package org.coffee.diary.javafx.catalog;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CatalogTreeItem extends TreeItem<String> {

	private Catalog catalog;

	public CatalogTreeItem(Catalog catalog) {

		this.catalog = catalog;
		setValue(catalog.getMenuName());
		Image image;
		if (catalog.isFolder()) {
			image = new Image(getClass().getResourceAsStream("/diary/img/folder_16.png"));
		} else {
			image = new Image(getClass().getResourceAsStream("/diary/img/file_16.png"));
		}
		setGraphic(new ImageView(image));
	}

	public Catalog getCatalog() {

		return catalog;
	}

	public void setCatalog(Catalog catalog) {

		this.catalog = catalog;
	}

}
