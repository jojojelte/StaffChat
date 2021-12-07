package me.jojojelte.staffchat.commands;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import me.jojojelte.staffchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class SendCommand implements CommandExecutor {
    private final Main main;

    public SendCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            FileConfiguration config = main.getConfig();

            if (player.isOp() || player.hasPermission("staffchat.use") || player.hasPermission("staffchat.*")) {
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

                ChatColor c1;
                ChatColor c2;

                ChatColor white = ChatColor.WHITE;

                String textcolor = config.getString("color.textcolor").toLowerCase();
                String nickcolor = config.getString("color.nickcolor").toLowerCase();



                c1 = ChatColor.getByChar(textcolor);
                c2 = ChatColor.getByChar(nickcolor);

                for (Player current : Bukkit.getOnlinePlayers()){
                    if (current.hasPermission("staffchat.staffchat") || current.isOp() || current.hasPermission("staffchat.*")){
                        current.sendMessage(c1 + "[" + white+ "StaffChat" + c1 + "]" + " " + c2+ playerName + white + ": " + c1 + content);
                    }
                }
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "You can't use this!");
                return true;
            }

        } else{
            main.getLogger().info("Console can't access the staff chat.");
            return true;
        }
    }
}
