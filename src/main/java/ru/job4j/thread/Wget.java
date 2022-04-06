package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(new URI(url).getPath()).getFileName().toString())) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            long passTime;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                passTime = System.currentTimeMillis() - startTime;
                if (passTime < speed) {
                    Thread.sleep(speed - passTime);
                }
                startTime = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("not enough arguments");
            System.exit(0);
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
