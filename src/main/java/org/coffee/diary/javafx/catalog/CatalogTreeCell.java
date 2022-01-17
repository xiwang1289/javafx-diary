package org.coffee.diary.javafx.catalog;

import org.coffee.diary.common.DiaryConstants;
import org.coffee.diary.service.NoteService;
import org.springframework.util.StringUtils;

import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.converter.DefaultStringConverter;

public class CatalogTreeCell extends TextFieldTreeCell<String> {

	private NoteService noteService;

	public CatalogTreeCell(NoteService noteService) {

		super(new DefaultStringConverter());
		this.noteService = noteService;
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
		noteService.updateCatalog(((CatalogTreeItem) tree.getRoot()).getCatalog());
	}
}
