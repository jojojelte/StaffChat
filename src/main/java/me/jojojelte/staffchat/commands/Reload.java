package me.jojojelte.staffchat.commands;

import me.jojojelte.staffchat.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Reload implements CommandExecutor {
    private final Main main;

    public Reload(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.isOp() || player.hasPermission("staffchat.reload") || player.hasPermission("staffchat.*")) {

            try {
                main.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "Succesfully reloaded the config file!");
                System.out.println("[StaffChat] Reloaded Config");
                return true;
            } catch (Error e) {
                player.sendMessage(ChatColor.RED + "Unable to reload the config.yml file!");
                System.out.println("Unable to reload config!");
                return true;
            }
        } else{
            player.sendMessage(ChatColor.RED + "You can't use this!");
            return true;
        }
    }
}
