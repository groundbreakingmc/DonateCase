package com.jodexindustries.donatecase.spigot;

import com.jodexindustries.donatecase.api.addon.Addon;
import com.jodexindustries.donatecase.api.scheduler.SchedulerTask;
import org.bukkit.scheduler.BukkitTask;

public final class BukkitSchedulerTask implements SchedulerTask {

    private final Addon owner;
    private final BukkitTask task;

    public BukkitSchedulerTask(Addon owner, BukkitTask task) {
        this.owner = owner;
        this.task = task;
    }

    @Override
    public void cancel() {
        task.cancel();
    }

    @Override
    public boolean isCancelled() {
        return task.isCancelled();
    }

    @Override
    public boolean isSync() {
        return task.isSync();
    }

    @Override
    public Addon getOwner() {
        return owner;
    }

}
