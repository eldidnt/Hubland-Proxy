package dev.didnt.proxy.hubland_proxy.commands;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.didnt.proxy.hubland_proxy.HubLand_proxy;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

public record HelpCommand(ProxyServer server, HubLand_proxy main, Logger logger) implements SimpleCommand {

    public HelpCommand(ProxyServer server, HubLand_proxy main, org.slf4j.Logger logger) {
        this.server = server;
        this.main = main;
        this.logger = logger;
        CommandManager manager = server.getCommandManager();
        manager.register(manager.metaBuilder("alert").build(), this);
    }


    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();

        String[] args = invocation.arguments();
        if (args.length != 1) return;
        if(args[0].equalsIgnoreCase("help"))
            source.sendMessage(Component.text(HubLand_proxy.getConfig().getString("messages.help")));
    }
}
