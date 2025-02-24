package com.shanebeestudios.coreapi.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * General utility class
 */
@SuppressWarnings("unused")
public class Utils {

    private Utils() {
    }

    private static final CommandSender CONSOLE = Bukkit.getConsoleSender();
    private static String PREFIX = "&7[&bCoreApi&7] ";
    private static String MINI_PREFIX = "<grey>[<aqua>CoreApi<grey>] ";
    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f\\d]){6}>");
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    /**
     * Set the prefix to be used in messages
     *
     * @param prefix Prefix to be used in messages
     * @deprecated Use {@link #setMiniPrefix(String)} instead
     */
    @Deprecated
    public static void setPrefix(String prefix) {
        PREFIX = prefix;
    }

    /**
     * Set the prefix to be used in mini messages
     *
     * @param prefix Prefix to be used in mini messages
     */
    public static void setMiniPrefix(String prefix) {
        MINI_PREFIX = prefix;
    }

    /**
     * Get a colored string, accepts HEX color codes
     *
     * @param string String to apply color to
     * @return Colored string
     * @deprecated Use {@link #getMini(String)} instead
     */
    @Deprecated
    public static String getColString(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find()) {
            final ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            final String before = string.substring(0, matcher.start());
            final String after = string.substring(matcher.end());
            string = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(string);
        }

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Get a {@link MiniMessage mini message} from string
     *
     * @param message String to convert to mini message
     * @return Mini message from string
     */
    public static Component getMini(String message) {
        return MINI_MESSAGE.deserialize(message);
    }

    /**
     * Log a formatted message to console
     *
     * @param message Formatted message
     * @param objects Objects for format
     * @deprecated Use {@link #logMini(String, Object...)} instead
     */
    @Deprecated
    public static void log(String message, Object... objects) {
        sendTo(CONSOLE, message, objects);
    }

    /**
     * Log a formatted mini-message to console
     *
     * @param message Formatted message
     * @param objects Objects for format
     */
    public static void logMini(String message, Object... objects) {
        sendMiniTo(CONSOLE, message, objects);
    }

    /**
     * Send a colored/prefixed message to a command sender
     *
     * @param sender  Sender to receive message
     * @param message Formatted message to send
     * @param objects Objects to include in format
     * @deprecated Use {@link #sendMiniTo(CommandSender, String, Object...)} instead
     */
    @Deprecated
    public static void sendTo(CommandSender sender, String message, Object... objects) {
        String format;
        if (objects.length > 0) {
            format = String.format(message, objects);
        } else {
            format = message;
        }
        sender.sendMessage(getColString(PREFIX + format));
    }

    /**
     * Send a colored/prefixed mini-message to a command sender
     *
     * @param sender  Sender to receive message
     * @param message Formatted message to send
     * @param objects Objects to include in format
     */
    public static void sendMiniTo(CommandSender sender, String message, Object... objects) {
        String format;
        if (objects.length > 0) {
            format = String.format(message, objects);
        } else {
            format = message;
        }
        sender.sendMessage(getMini(MINI_PREFIX + format));
    }

    /**
     * Broadcast a message to all players and console
     *
     * @param message Formatted message to send
     * @param objects Objects to include in format
     * @deprecated Use {@link #broadcastMini(String, Object...)} instead
     */
    @Deprecated
    public static void broadcast(String message, Object... objects) {
        String format;
        if (objects.length > 0) {
            format = String.format(message, objects);
        } else {
            format = message;
        }
        Bukkit.broadcastMessage(getColString(PREFIX + format));
    }

    /**
     * Broadcast a mini-message to all players and console
     *
     * @param message Formatted message to send
     * @param objects Objects to include in format
     */
    public static void broadcastMini(String message, Object... objects) {
        String format;
        if (objects.length > 0) {
            format = String.format(message, objects);
        } else {
            format = message;
        }
        Bukkit.broadcast(getMini(MINI_PREFIX + format));
    }

}
