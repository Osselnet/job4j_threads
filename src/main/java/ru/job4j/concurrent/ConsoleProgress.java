package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = {'\\', '|', '/', '-'};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (char s : process) {
                    System.out.print("\r load: " + s);
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
