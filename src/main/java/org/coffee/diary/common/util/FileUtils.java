package org.coffee.diary.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FileUtils {

	private final static Logger log = LoggerFactory.getLogger(FileUtils.class);

	public static void writeToFile(String filePath, String text) {

		if (!StringUtils.isEmpty(text)) {
			try {
				FileOutputStream fos = new FileOutputStream(filePath, false);
				FileChannel channel = fos.getChannel();
				// StringBuffer content = new StringBuffer();
				ByteBuffer buf = ByteBuffer.wrap(text.getBytes());
				buf.put(text.getBytes());
				buf.flip();
				channel.write(buf);
				channel.close();
				fos.close();
			} catch (IOException e) {
				log.error("The file write failed", e);
			}
		}
	}

	public static String readFile(String filePath) {

		StringBuffer sb = new StringBuffer();
		try {
			Files.lines(Paths.get(filePath)).forEach(line -> {
				sb.append(line.trim());
			});
		} catch (IOException e) {
			log.error("The catalog loading failed", e);
		}
		return sb.toString();
	}

	public static void copy(String sourceFilePath, String targetFilePath) throws IOException {

		if (!StringUtils.isEmpty(sourceFilePath) &&
				!StringUtils.isEmpty(targetFilePath)) {
			Files.copy(
					Paths.get(sourceFilePath),
					Paths.get(targetFilePath));
		}
	}
}
