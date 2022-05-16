package me.rylan;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.typesafe.config.*;

public class BotConfig {
    private Path path;
    private String token;
    private static String prefix;
    private OnlineStatus status;
    private Activity activity;
    private String[] filteredWords;

    public void exec() {
        try {
            path = getConfigPath();

            Config config = ConfigFactory.load();

            // Set Config Values
            token = config.getString("token");
            prefix = config.getString("prefix");
            status = OnlineStatus.valueOf(config.getString("status").toUpperCase());
            activity = Activity.playing((config.getString("activity")));

            if (token == null || token.equalsIgnoreCase("BOT_TOKEN")) {
                //TODO Create better interface for the user
                System.out.print("Enter your bot token: ");
                token = String.valueOf(new Scanner(System.in).nextLine());

                if (token == null) {
                    System.out.println("No token provided!");
                } else {
                    updateConfig();
                }
            }
        }
        catch (ConfigException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void generateConfig() {
        Path path = getConfigPath();
        byte[] bytes = loadDefaultConfig().getBytes();

        try {
            System.out.println("Writing default cong file to " + path.toAbsolutePath());
            Files.write(path, bytes);
        }
        catch(Exception ex) {
            System.out.println("An error occurred writing the default config file: " + ex.getMessage());
        }
    }

    public String loadDefaultConfig() {
        String configText;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new Bot().getClass().getResourceAsStream("/default.conf")))) {
            StringBuilder stringBuilder = new StringBuilder();
            reader.lines().forEach(line -> stringBuilder.append("\r\n").append(line));
            configText = stringBuilder.toString().trim();
        } catch (IOException ex) {
            configText = null;
        }

        return configText;
    }

    private void updateConfig() {
        byte[] bytes = loadDefaultConfig().replace("BOT_TOKEN", token).trim().getBytes();

        try {
            Files.write(path, bytes);
        }
        catch(IOException ex) {
            System.out.println("Error writing changes to file: " + ex);
        }
    }

    public Path getConfigPath() {
        Path path = Path.of((System.getProperty("config.file", System.getProperty("config", "config.txt"))));

        if (path.toFile().exists())
        {
            if(System.getProperty("config.file") == null)
                System.setProperty("config.file", System.getProperty("config", path.toAbsolutePath().toString()));
            ConfigFactory.invalidateCaches();
        }
        return path;
    }

    public String getToken() {
        return token;
    }

    public static String getPrefix() {
        return prefix;
    }

    public OnlineStatus getOnlineStatus() {
        return status;
    }

    public Activity getActivity() {
        return activity;
    }

    public String[] getFilteredWords() {
        return filteredWords;
    }
}