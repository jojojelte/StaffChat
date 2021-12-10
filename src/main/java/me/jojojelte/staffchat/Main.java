package me.jojojelte.staffchat;

import me.jojojelte.staffchat.commands.AdminChat;
import me.jojojelte.staffchat.commands.Reload;
import me.jojojelte.staffchat.commands.SendCommand;
import me.jojojelte.staffchat.commands.updateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private Boolean newUpdate = false;

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig();

        System.out.println("[StaffChat] StaffChat can now be used.");
        getServer().getPluginCommand("sc").setExecutor(new SendCommand(this));
        getServer().getPluginCommand("ac").setExecutor(new AdminChat(this));
        getServer().getPluginCommand("screload").setExecutor(new Reload(this));

        new updateChecker(this, 98171).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is no new update available.");
            } else {
                newUpdate = true;
                getLogger().info("A new update is available: \n» https://www.spigotmc.org/resources/staffchat.98171/");
            }
        });
        registerEvents();
    }

    @Override
    public void onDisable() {
        System.out.println("[StaffChat] Thank you for using StaffChat!");
        // Plugin shutdown logic
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
    }

    public class JoinEvent implements Listener {
        public Server plugin;

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event)
        {
            if(newUpdate){
                if(event.getPlayer().isOp() || event.getPlayer().hasPermission("staffchat.admin") || event.getPlayer().hasPermission("staffchat.*")){
                    Player player = event.getPlayer();
                    player.sendMessage(ChatColor.AQUA + "[StaffChat]" + ChatColor.GRAY + " A new update is available! Download here:" + ChatColor.BLUE + "\n» https://www.spigotmc.org/resources/staffchat.98171/");
                }
            }

        }

    }
}
