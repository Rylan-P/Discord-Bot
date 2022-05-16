package me.rylan;

import me.rylan.listeners.Listener;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.nio.file.Path;

public class Bot {
    public static void main(String[] args) {
        BotConfig config = new BotConfig();
        Path path = config.getConfigPath();

        if(!path.toFile().exists()) {
            config.generateConfig();
        }
        initializeBot(config);
    }

    public static void initializeBot(BotConfig config) {
        config.exec();

        try {
            JDABuilder builder = JDABuilder.createDefault(config.getToken())
                    .setStatus(config.getOnlineStatus())
                    .setActivity(config.getActivity())
                    .addEventListeners(new Listener());
            builder.build();
        } catch (LoginException ex) {
            System.out.println("Error initializing bot: " + ex);
            System.exit(1);
        }
    }
}