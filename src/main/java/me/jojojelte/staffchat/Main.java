package me.jojojelte.staffchat;

import me.jojojelte.staffchat.commands.AdminChat;
import me.jojojelte.staffchat.commands.Reload;
import me.jojojelte.staffchat.commands.SendCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig();

        System.out.println("[StaffChat] StaffChat can now be used.");
        getServer().getPluginCommand("sc").setExecutor(new SendCommand(this));
        getServer().getPluginCommand("screload").setExecutor(new Reload(this));
        getServer().getPluginCommand("ac").setExecutor(new AdminChat(this));


    }

    @Override
    public void onDisable() {
        System.out.println("[StaffChat] Thank you for using StaffChat!");
        // Plugin shutdown logic
    }
}
