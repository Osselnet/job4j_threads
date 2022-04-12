package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s.\n", user.getUsername(), user.getEmail());
        String body = format("Add a new event to %s.\n", user.getUsername());

        pool.submit(() -> {
           send(subject, body, user.getEmail());
        });
    }

     public void close() {
         pool.shutdown();
         while (!pool.isTerminated()) {
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }
         }
    }

    private void send(String subject, String body, String email) {

    }
}
