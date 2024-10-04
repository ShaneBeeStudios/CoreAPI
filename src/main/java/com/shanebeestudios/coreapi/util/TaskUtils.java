package com.shanebeestudios.coreapi.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Shortcut methods for running tasks
 */
@SuppressWarnings("unused")
public class TaskUtils {

    private TaskUtils() {
    }

    private static Plugin PLUGIN;
    private static final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

    /**
     * Initialize this util with a Plugin
     *
     * @param plugin Plugin used in schedulers
     */
    public static void init(Plugin plugin) {
        if (PLUGIN != null) {
            throw new IllegalStateException("Plugin is already initialized.");
        }
        PLUGIN = plugin;
    }

    /**
     * Run a task at the end of the current tick
     *
     * @param runnable Task to run
     */
    public static void runTaskEndOfTick(Runnable runnable) {
        runTaskLater(runnable, 0);
    }

    /**
     * Run a task at the end of the current tick async
     *
     * @param runnable Task to run
     */
    public static void runTaskEndOfTickAsynchronously(Runnable runnable) {
        runTaskLaterAsynchronously(runnable, 0);
    }

    /**
     * Run a task later
     *
     * @param runnable Task to run
     * @param delay    Delay in ticks before task runs
     */
    public static void runTaskLater(Runnable runnable, long delay) {
        pluginCheck();
        SCHEDULER.runTaskLater(PLUGIN, runnable, delay);
    }

    /**
     * Run a task later async
     *
     * @param runnable Task to run
     * @param delay    Delay in ticks before task runs
     */
    public static void runTaskLaterAsynchronously(Runnable runnable, long delay) {
        pluginCheck();
        SCHEDULER.runTaskLaterAsynchronously(PLUGIN, runnable, delay);
    }

    /**
     * Run a task timer
     *
     * @param runnable Task to run
     * @param delay    Delay in ticks before task runs
     * @param period   Period in ticks how often task runs
     */
    public static void runTaskTimer(Runnable runnable, long delay, long period) {
        pluginCheck();
        SCHEDULER.runTaskTimer(PLUGIN, runnable, delay, period);
    }

    /**
     * Run a task timer async
     *
     * @param runnable Task to run
     * @param delay    Delay in ticks before task runs
     * @param period   Period in ticks how often task runs
     */
    public static void runTaskTimerAsynchronously(Runnable runnable, long delay, long period) {
        pluginCheck();
        SCHEDULER.runTaskTimerAsynchronously(PLUGIN, runnable, delay, period);
    }

    private static void pluginCheck() {
        if (PLUGIN == null) {
            throw new IllegalStateException("Plugin is not initialized, run 'TaskUtils.init(Plugin)' first.");
        }
    }

}
