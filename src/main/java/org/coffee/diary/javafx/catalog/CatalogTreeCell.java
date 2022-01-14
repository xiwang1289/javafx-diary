package org.coffee.diary.javafx.catalog;

import org.coffee.diary.common.DiaryConstants;
import org.coffee.diary.service.CatalogService;
import org.springframework.util.StringUtils;

import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.converter.DefaultStringConverter;

public class CatalogTreeCell extends TextFieldTreeCell<String> {

	private CatalogService catalogService;

	public CatalogTreeCell(CatalogService catalogService) {

		super(new DefaultStringConverter());
		this.catalogService = catalogService;
	}

	@Override
	public void commitEdit(String newValue) {

		if (StringUtils.isEmpty(newValue)) {
			newValue = DiaryConstants.DEFAULT_CATALOG_NAME;
		}
		super.commitEdit(newValue);
		afterEdit();
	}

	private void afterEdit() {

		CatalogTreeItem item = (CatalogTreeItem) super.getTreeItem();
		item.getCatalog().setMenuName(item.getValue());
		TreeView<String> tree = super.getTreeView();
		catalogService.updateCatalog(((CatalogTreeItem) tree.getRoot()).getCatalog());
	}
}
