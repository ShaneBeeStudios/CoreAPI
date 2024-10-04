package com.shanebeestudios.coreapi.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Shortcut methods for running tasks
 */
@SuppressWarnings("unused")
public class TaskUtils {

    private static Plugin PLUGIN;
    private static final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

    /** Initialize this util with a Plugin
     * @param plugin Plugin used in schedulers
     */
    public static void init(Plugin plugin) {
        PLUGIN = plugin;
    }

    public static void runTaskEndOfTick(Runnable runnable) {
        runTaskLater(runnable, 0);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        SCHEDULER.runTaskLater(PLUGIN, runnable, delay);
    }

    public static void runTaskEndOfTickAsynchronously(Runnable runnable) {
        runTaskLaterAsynchronously(runnable, 0);
    }

    public static void runTaskLaterAsynchronously(Runnable runnable, long delay) {
        SCHEDULER.runTaskLaterAsynchronously(PLUGIN, runnable, delay);
    }

    public static void runTaskTimer(Runnable runnable, long delay, long period) {
        SCHEDULER.runTaskTimer(PLUGIN, runnable, delay, period);
    }

    public static void runTaskTimerAsynchronously(Runnable runnable, long delay, long period) {
        SCHEDULER.runTaskTimerAsynchronously(PLUGIN, runnable, delay, period);
    }

}
