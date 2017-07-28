package de.marvin2k0.votingsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin
{
    private String prefix = "§7[§9Voting§7] ";
    @Override
    public void onEnable()
    {
        getCommand("createvote").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player && !((Player) sender).hasPermission("voting.create"))
        {
            sender.sendMessage(prefix + "§cDafür hast du keine Rechte.");

            return true;
        }

        if (!(args.length >= 1))
        {
            sender.sendMessage(prefix + "§cBitte benutze /createvote <titel>");

            return true;
        }

        String title = "";

        for (int i = 0; i < args.length; i++)
            title += args[i] + " ";

        VoteManager voteManager = new VoteManager();
        voteManager.createVote(title);

        return true;
    }

    private class VoteManager
    {
        public ArrayList<String> activeVotes = new ArrayList<>();

        public void createVote(String title)
        {
            activeVotes.add(title);
        }
    }
}
