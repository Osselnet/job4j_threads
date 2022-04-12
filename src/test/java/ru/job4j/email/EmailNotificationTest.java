package ru.job4j.email;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailNotificationTest {

    @Test
    public void emailTo() {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Никола Питерский", "np@gmail.com"));
        emailNotification.emailTo(new User("Василий Пупкин", "vp@gmail.com"));
        emailNotification.emailTo(new User("Иван Запупыркин", "ivan_ppp@gmail.com"));
        emailNotification.close();
    }
}