package com.jodexindustries.donatecase.spigot;

import com.jodexindustries.donatecase.api.addon.Addon;
import com.jodexindustries.donatecase.api.scheduler.Scheduler;
import com.jodexindustries.donatecase.api.scheduler.SchedulerTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public class BukkitScheduler implements Scheduler {

    private final Plugin plugin;
    private final org.bukkit.scheduler.BukkitScheduler scheduler;

    public BukkitScheduler(BukkitBackend backend) {
        this.plugin = backend.getPlugin();
        this.scheduler = plugin.getServer().getScheduler();
    }

    private BukkitSchedulerTask wrapper(Addon addon, BukkitTask task) {
        return new BukkitSchedulerTask(addon, task);
    }

    @Override
    public SchedulerTask run(Addon addon, Runnable task) {
        BukkitTask bukkitTask = scheduler.runTask(plugin, task);
        return wrapper(addon, bukkitTask);
    }

    @Override
    public SchedulerTask run(Addon addon, Runnable task, long delay) {
        BukkitTask bukkitTask = scheduler.runTaskLater(plugin, task, delay);
        return wrapper(addon, bukkitTask);
    }

    @Override
    public SchedulerTask run(Addon addon, Runnable task, long delay, long period) {
        BukkitTask bukkitTask = scheduler.runTaskTimer(plugin, task, delay, period);
        return wrapper(addon, bukkitTask);
    }

    @Override
    public void run(Addon addon, Consumer<SchedulerTask> task) {
        scheduler.runTask(plugin, (bukkitTask) -> {
            BukkitSchedulerTask wrappedTask = wrapper(addon, bukkitTask);
            task.accept(wrappedTask);
        });
    }

    @Override
    public void run(Addon addon, Consumer<SchedulerTask> task, long delay) {
        scheduler.runTaskLater(plugin, (bukkitTask) -> {
            BukkitSchedulerTask wrappedTask = wrapper(addon, bukkitTask);
            task.accept(wrappedTask);
        }, delay);
    }

    @Override
    public void run(Addon addon, Consumer<SchedulerTask> task, long delay, long period) {
        scheduler.runTaskTimer(plugin, (bukkitTask) -> {
            BukkitSchedulerTask wrappedTask = wrapper(addon, bukkitTask);
            task.accept(wrappedTask);
        }, delay, period);
    }

    @Override
    public SchedulerTask async(Addon addon, Runnable task, long delay) {
        BukkitTask bukkitTask = scheduler.runTaskLaterAsynchronously(plugin, task, delay);
        return wrapper(addon, bukkitTask);
    }

    @Override
    public SchedulerTask async(Addon addon, Runnable task, long delay, long period) {
        BukkitTask bukkitTask = scheduler.runTaskTimerAsynchronously(plugin, task, delay, period);
        return wrapper(addon, bukkitTask);
    }

    @Override
    public void async(Addon addon, Consumer<SchedulerTask> task, long delay) {
        scheduler.runTaskLaterAsynchronously(plugin, (bukkitTask) -> {
            BukkitSchedulerTask wrappedTask = wrapper(addon, bukkitTask);
            task.accept(wrappedTask);
        }, delay);
    }

    @Override
    public void async(Addon addon, Consumer<SchedulerTask> task, long delay, long period) {
        scheduler.runTaskTimerAsynchronously(plugin, (bukkitTask) -> {
            BukkitSchedulerTask wrappedTask = wrapper(addon, bukkitTask);
            task.accept(wrappedTask);
        }, delay, period);
    }

    @Override
    public void cancel(int taskId) {
        scheduler.cancelTask(taskId);
    }

    @Override
    public void shutdown() {
        scheduler.cancelTasks(plugin);
    }
}
