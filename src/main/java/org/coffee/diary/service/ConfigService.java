package org.coffee.diary.service;

import org.coffee.diary.autoconfigure.DiaryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

	@Autowired
	private DiaryProperties diaryProperties;

	public String getWorkspace() {

		return diaryProperties.getWorkspace();
	}
}
