package org.coffee.diary.javafx.catalog;

import java.util.ArrayList;
import java.util.UUID;

import org.coffee.diary.common.DiaryConstants;
import org.coffee.diary.service.CatalogService;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TreeView;

public class CalalogMouseMenu extends ContextMenu {

	private CatalogService		catalogService;
	private TreeView<String>	catalogTree;

	public CalalogMouseMenu(
			CatalogService catalogService,
			TreeView<String> catalogTree) {

		this.catalogService = catalogService;
		this.catalogTree = catalogTree;
	}

	public void getInstance() {

		MenuItem item11 = new MenuItem("新建子层文档");
		item11.setOnAction(event -> {
			Catalog newCatalog = new Catalog();
			newCatalog.setChildren(new ArrayList<Catalog>());
			newCatalog.setFolder(false);
			newCatalog.setId(UUID.randomUUID().toString());
			newCatalog.setMenuName(DiaryConstants.DEFAULT_CATALOG_NAME);
			CatalogTreeItem newItem = new CatalogTreeItem(newCatalog);
			CatalogTreeItem parentItem = (CatalogTreeItem) catalogTree.getSelectionModel().getSelectedItem();
			parentItem.setExpanded(true);
			parentItem.getChildren().add(newItem);
			parentItem.getCatalog().getChildren().add(newCatalog);
			catalogService.updateCatalog(((CatalogTreeItem) catalogTree.getRoot()).getCatalog());
		});
		MenuItem item12 = new MenuItem("新建相邻文档");
		item12.setOnAction(event -> {
			Catalog newCatalog = new Catalog();
			newCatalog.setChildren(new ArrayList<Catalog>());
			newCatalog.setFolder(false);
			newCatalog.setId(UUID.randomUUID().toString());
			newCatalog.setMenuName(DiaryConstants.DEFAULT_CATALOG_NAME);
			CatalogTreeItem newItem = new CatalogTreeItem(newCatalog);
			CatalogTreeItem parentItem = (CatalogTreeItem) catalogTree.getSelectionModel().getSelectedItem()
					.getParent();
			parentItem.setExpanded(true);
			parentItem.getChildren().add(newItem);
			parentItem.getCatalog().getChildren().add(newCatalog);
			catalogService.updateCatalog(((CatalogTreeItem) catalogTree.getRoot()).getCatalog());
		});
		getItems().add(item11);
		getItems().add(item12);
		// 插入分隔线
		getItems().add(new SeparatorMenuItem());

		MenuItem item21 = new MenuItem("新建子层文件夹");
		item21.setOnAction(event -> {
			Catalog newCatalog = new Catalog();
			newCatalog.setChildren(new ArrayList<Catalog>());
			newCatalog.setFolder(true);
			newCatalog.setId(UUID.randomUUID().toString());
			newCatalog.setMenuName(DiaryConstants.DEFAULT_CATALOG_NAME);
			CatalogTreeItem newItem = new CatalogTreeItem(newCatalog);
			CatalogTreeItem parentItem = (CatalogTreeItem) catalogTree.getSelectionModel().getSelectedItem();
			parentItem.setExpanded(true);
			parentItem.getChildren().add(newItem);
			parentItem.getCatalog().getChildren().add(newCatalog);
			catalogService.updateCatalog(((CatalogTreeItem) catalogTree.getRoot()).getCatalog());
		});
		getItems().add(item21);
		// 插入分隔线
		getItems().add(new SeparatorMenuItem());

		MenuItem item31 = new MenuItem("删除");
		item31.setOnAction(event -> {
			CatalogTreeItem parentItem = (CatalogTreeItem) catalogTree.getSelectionModel().getSelectedItem()
					.getParent();
			CatalogTreeItem selectItem = (CatalogTreeItem) catalogTree.getSelectionModel().getSelectedItem();
			parentItem.getChildren().remove(selectItem);
			parentItem.getCatalog().getChildren().remove(selectItem.getCatalog());
			catalogService.updateCatalog(((CatalogTreeItem) catalogTree.getRoot()).getCatalog());
		});
		MenuItem item32 = new MenuItem("重命名");
		item32.setOnAction(event -> {
			CatalogTreeItem selectItem = (CatalogTreeItem) catalogTree.getSelectionModel().getSelectedItem();
			catalogTree.edit(selectItem);
		});
		getItems().add(item31);
		getItems().add(item32);
	}
}
