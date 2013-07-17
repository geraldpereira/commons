package com.byob.files.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class ZipUtils {

	private static final int BUFFER_SIZE = 2048;

	private ZipUtils() {
	}

	public static InputStream unzipStream(final InputStream inputStream) throws IOException {
		final ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
		final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		final ZipEntry entry = zipInputStream.getNextEntry();

		if (entry == null) {
			throw new IllegalArgumentException("The zip stream is empty");
		}

		if (entry.isDirectory()) {
			throw new IllegalArgumentException(
					"The zip stream contains a folder");
		}

		byte[] buff = new byte[BUFFER_SIZE];
		int len;
		while ((len = zipInputStream.read(buff)) > 0) {
			memoryBuffer.write(buff, 0, len);
		}
		memoryBuffer.flush();

		return new ByteArrayInputStream(memoryBuffer.toByteArray());
	}

}
