package org.coffee.diary.javafx.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {

	private static final long	serialVersionUID	= -8470348575486386188L;
	private String				id;
	private String				menuName;
	private boolean				isFolder;
	private String				filePath;
	private List<Catalog>		children			= new ArrayList<Catalog>();

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getMenuName() {

		return menuName;
	}

	public void setMenuName(String menuName) {

		this.menuName = menuName;
	}

	public boolean isFolder() {

		return isFolder;
	}

	public void setFolder(boolean isFolder) {

		this.isFolder = isFolder;
	}

	public String getFilePath() {

		return filePath;
	}

	public void setFilePath(String filePath) {

		this.filePath = filePath;
	}

	public List<Catalog> getChildren() {

		return children;
	}

	public void setChildren(List<Catalog> children) {

		this.children = children;
	}

	@Override
	public String toString() {

		return "CatalogModel [id=" + id + ", menuName=" + menuName + ", isFolder=" + isFolder
				+ ", children=" + children + "]";
	}

}
