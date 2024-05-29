package dev.didnt.proxy.hubland_proxy.commands;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.didnt.proxy.hubland_proxy.HubLand_proxy;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.slf4j.Logger;

public record BuyCommand(ProxyServer server, HubLand_proxy main, Logger logger) implements SimpleCommand {

    public BuyCommand(ProxyServer server, HubLand_proxy main, Logger logger) {
        this.server = server;
        this.main = main;
        this.logger = logger;
        CommandManager manager = server.getCommandManager();
        manager.register(manager.metaBuilder("buy").build(), this);
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        Audience player = source;
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize("Visita nuestra <light_purple>tienda</light_purple> y descubre increíbles <aqua>mejoras</aqua> para tu cuenta <b><yellow><click:open_url:'https://store.hubland.gg'><hover:show_text:'<green>Haz click para abrir!</green>'>¡CLICK AQUI!</hover></click></yellow></b>");
        player.sendMessage(parsed);
    }
}

