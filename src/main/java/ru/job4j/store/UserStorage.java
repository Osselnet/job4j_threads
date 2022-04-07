package ru.job4j.store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public final class UserStorage {
    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<User> userFromOpt = searchUser(fromId);
        Optional<User> userToOpt = searchUser(toId);
        if (userFromOpt.isPresent() && userToOpt.isPresent()) {
            userFromOpt.get().setAmount(userFromOpt.get().getAmount() - amount);
            userToOpt.get().setAmount(userToOpt.get().getAmount() + amount);
            return true;
        }
        return false;
    }

    private synchronized Optional<User> searchUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
