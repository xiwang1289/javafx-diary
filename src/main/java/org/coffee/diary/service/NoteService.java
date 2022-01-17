package org.coffee.diary.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.coffee.diary.autoconfigure.DiaryProperties;
import org.coffee.diary.common.util.FileUtils;
import org.coffee.diary.javafx.catalog.Catalog;
import org.coffee.diary.javafx.catalog.CatalogTreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class NoteService implements InitializingBean {

	private final Logger	log	= LoggerFactory.getLogger(NoteService.class);

	@Autowired
	private ConfigService	configService;
	@Autowired
	private DiaryProperties	diaryProperties;
	@Autowired
	private Gson			gson;

	// 工作空间中目录文件
	private String			FILEPATH;

	public CatalogTreeItem readCatalog() {

		String catalogJson = FileUtils.readFile(FILEPATH);
		if (!StringUtils.isEmpty(catalogJson)) {
			Catalog model = gson.fromJson(catalogJson, Catalog.class);
			CatalogTreeItem root = new CatalogTreeItem(model);
			root.setExpanded(true);
			getChildren(model.getChildren(), root);
			return root;
		}
		return null;
	}

	public void updateCatalog(Catalog catalog) {

		Gson printGson = new GsonBuilder().setPrettyPrinting().create();
		FileUtils.writeToFile(FILEPATH, printGson.toJson(catalog));
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		// 如果文件存在，则读取文件，放入缓存
		// 如果文件不存在，则根据默认文件初始化
		FILEPATH = configService.getWorkspace()
				+ File.separator
				+ diaryProperties.getDataPath()
				+ File.separator
				+ diaryProperties.getCatalogFileName();
		if (!Files.exists(Paths.get(FILEPATH))) {
			String rawFilePath = NoteService.class.getResource("/diary/config/DefaultCatalog.json").getFile();
			FileUtils.copy(rawFilePath, FILEPATH);
		}

	}

	private void getChildren(List<Catalog> children, CatalogTreeItem parent) {

		if (children != null && children.size() > 0) {
			for (Catalog tmp : children) {
				CatalogTreeItem item = new CatalogTreeItem(tmp);
				parent.getChildren().add(item);
				if (tmp.getChildren() != null && tmp.getChildren().size() > 0) {
					getChildren(tmp.getChildren(), item);
				}
			}
		}
	}

}
