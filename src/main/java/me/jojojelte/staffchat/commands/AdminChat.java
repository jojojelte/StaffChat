package me.jojojelte.staffchat.commands;

import me.jojojelte.staffchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class AdminChat implements CommandExecutor {
    private final Main main;

    public AdminChat(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            FileConfiguration config = main.getConfig();

            if (player.isOp() || player.hasPermission("staffchat.adminchat") || player.hasPermission("staffchat.*")){
                String playerName = player.getName();

                StringBuilder sb = new StringBuilder();
                for (String arg : args) {
                    sb.append(arg).append(" ");
                }

                String content = sb.toString().trim();

                if (content == ""){
                    player.sendMessage(ChatColor.RED + "You can't send an empty message");
                    return true;
                }

                ChatColor textc;
                ChatColor nickc;

                ChatColor white = ChatColor.WHITE;
                String textcolor = config.getString("admincolor.textcolor").toLowerCase();
                String nickcolor = config.getString("admincolor.nickcolor").toLowerCase();

                textc = ChatColor.getByChar(textcolor);
                nickc = ChatColor.getByChar(nickcolor);

                for (Player current : Bukkit.getOnlinePlayers()){
                    if (current.hasPermission("staffchat.adminchat") || current.isOp() || current.hasPermission("staffchat.*")){
                        current.sendMessage(nickc + "[" + textc + "AdminChat" + nickc + "]" + " " + nickc + playerName + white + ": " + textc + content);
                    }
                }
                return true;

            }else{
                player.sendMessage(ChatColor.RED + "You can't use this!");
                return true;
            }
        } else{
            main.getLogger().info("Console can't access the admin chat.");
            return true;
        }
    }
}
