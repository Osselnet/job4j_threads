package ru.job4j.io;

import java.io.*;

public final class SaveFile {

    private final File file;
    private final String content;

    public SaveFile(File file, String content) {
        this.file = file;
        this.content = content;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}