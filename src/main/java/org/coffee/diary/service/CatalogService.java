package org.coffee.diary.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.coffee.diary.autoconfigure.DiaryProperties;
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
public class CatalogService implements InitializingBean {

	private final Logger	log	= LoggerFactory.getLogger(CatalogService.class);

	@Autowired
	private ConfigService	configService;
	@Autowired
	private DiaryProperties	diaryProperties;
	@Autowired
	private Gson			gson;

	// 工作空间中目录文件
	private String			FILEPATH;

	public CatalogTreeItem readCatalog() {

		String catalogJson = readCatalogJson();
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
		try {
			writeCatalog(printGson.toJson(catalog));
		} catch (IOException e) {
			log.error("The catalog file write failed", e);
		}
	}

	private void writeCatalog(String catalogJson) throws IOException {

		if (!StringUtils.isEmpty(catalogJson)) {
			FileOutputStream fos = new FileOutputStream(FILEPATH, false);
			FileChannel channel = fos.getChannel();
			// StringBuffer content = new StringBuffer();
			ByteBuffer buf = ByteBuffer.wrap(catalogJson.getBytes());
			buf.put(catalogJson.getBytes());
			buf.flip();
			channel.write(buf);
			channel.close();
			fos.close();
		}
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
			String rawFilePath = CatalogService.class.getResource("/diary/config/DefaultCatalog.json").getFile();
			Files.copy(
					Paths.get(rawFilePath),
					Paths.get(FILEPATH));
		}

	}

	private String readCatalogJson() {

		StringBuffer sb = new StringBuffer();
		try {
			Files.lines(Paths.get(FILEPATH)).forEach(line -> {
				sb.append(line.trim());
			});
		} catch (IOException e) {
			log.error("The catalog loading failed", e);
		}
		return sb.toString();
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
