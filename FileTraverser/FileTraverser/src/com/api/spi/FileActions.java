package com.api.spi;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public interface FileActions {

	public void performAction(Path file,
            BasicFileAttributes attrs);
}
