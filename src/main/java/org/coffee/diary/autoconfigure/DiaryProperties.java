package org.coffee.diary.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "diary")
public class DiaryProperties {

	private String	workspace;
	private String	configPath;
	private String	configFileName;
	private String	dataPath;
	private String	catalogFileName;

	public String getWorkspace() {

		return workspace;
	}

	public void setWorkspace(String workspace) {

		this.workspace = workspace;
	}

	public String getConfigFileName() {

		return configFileName;
	}

	public void setConfigFileName(String configFileName) {

		this.configFileName = configFileName;
	}

	public String getCatalogFileName() {

		return catalogFileName;
	}

	public void setCatalogFileName(String catalogFileName) {

		this.catalogFileName = catalogFileName;
	}

	public String getConfigPath() {

		return configPath;
	}

	public void setConfigPath(String configPath) {

		this.configPath = configPath;
	}

	public String getDataPath() {

		return dataPath;
	}

	public void setDataPath(String dataPath) {

		this.dataPath = dataPath;
	}

}
