package dev.didnt.proxy.hubland_proxy;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.didnt.proxy.hubland_proxy.commands.HelpCommand;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Plugin(
        id = "hubland_proxy",
        name = "HubLand Proxy",
        version = "1.0-SNAPSHOT",
        authors = {"Didnt_"}
)
public class HubLand_proxy {
    private final ProxyServer server;
    private Logger logger;
    private static String configPath;

    @Inject
    public HubLand_proxy(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        configPath = "./plugins/HubLand/config.toml";

        logger.info("HubLand Proxy has been enabled.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        final var config = Path.of("/plugins/HubLand/config.toml");
        if(!Files.exists(config)) {
            try (final var configTemplate = getClass().getResourceAsStream("/config.toml")) {
                Files.createDirectories(config.getParent());
                assert configTemplate != null;
                Files.write(config, configTemplate.readAllBytes(), StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                logger.error("There was an error creating the config file! Report the stacktrace to https://github.com/xSehrMotiviert/velocityplus/issues", e);
            }
        }

        logger.info("""
                
                
                ██╗░░██╗██╗░░░██╗██████╗░██╗░░░░░░█████╗░███╗░░██╗██████╗░
                ██║░░██║██║░░░██║██╔══██╗██║░░░░░██╔══██╗████╗░██║██╔══██╗
                ███████║██║░░░██║██████╦╝██║░░░░░███████║██╔██╗██║██║░░██║
                ██╔══██║██║░░░██║██╔══██╗██║░░░░░██╔══██║██║╚████║██║░░██║
                ██║░░██║╚██████╔╝██████╦╝███████╗██║░░██║██║░╚███║██████╔╝
                ╚═╝░░╚═╝░╚═════╝░╚═════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
                
                Version: 1.0
                Author: Didnt_
                
                """);

        new HelpCommand(server, this, logger);
    }

    public static String getConfigPath() {
        return configPath;
    }

    public static Toml getConfig(){
        return new Toml().read(new File(getConfigPath()));
    }
}
