package com.github.dragonhht.database.manager.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件操作工具.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
public enum FileUtil {
    /** 实例. */
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取文件内容.
     * @param filePath
     * @return
     */
    public byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(file);
        }
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * 讲内容写入文件.
     * @param filePath
     */
    public void writeFile(String filePath, byte[] bytes, StandardOpenOption mode) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            this.createFile(file);
        }
        Path path = file.toPath();
        if (mode == StandardOpenOption.WRITE) {
            Files.write(path, bytes);
            return;
        }
        Files.write(path, bytes, mode);
    }

    /**
     * 创建多级目录的文件.
     * @param file
     */
    private void createFile(File file) throws IOException {
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
    }
}
