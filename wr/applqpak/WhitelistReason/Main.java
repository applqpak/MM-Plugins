package applqpak.WhitelistReason;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Config;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.Player;

import java.io.File;
import java.util.*;

public class Main extends PluginBase
{

  public Config config;

  public String VERSION = "v2.0.0";

  public String USAGE = "/whitelistreason <version | add | set | enable | disable> <player | message>";

  public String implode(String glue, String[] strArray)
  {

    String ret = "";

    for(int i = 0; i < strArray.length; i++)
    {

      if(strArray[i].trim() != "")
      {

        ret += (i == strArray.length - 1) ? strArray[i] : strArray[i] + glue;

      }

    }

    return ret;

  }

  public boolean in_array(String key, String[] strArray)
  {

    boolean keyExists = false;

    for(String item : strArray)
    {

      if(item == key)
      {

        keyExists = true;

      }

    }

    if(keyExists == true)
    {

      return true;

    }
    else
    {

      return false;

    }

  }

  public String[] toS(List lst)
  {

    String[] strArray = new String[lst.size()];

    int index = 0;

    for(Object value : lst)
    {

      strArray[index] = String.valueOf(value);

      index++;

    }

    return strArray;

  }

  @Override

  public void onEnable()
  {

    this.getDataFolder().mkdirs();

    if(!(new File(this.getDataFolder(), "config.yml").exists()))
    {

      saveResource("config.yml");

    }

    this.config = getConfig();

    this.getServer().getPluginManager().registerEvents(new EventListener(this), this);

    this.getLogger().info(TextFormat.GREEN + "Enabled.");

  }

  @Override

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {

    switch(cmd.getName())
    {

      case "whitelistreason":

        if(args.length == 0)
        {

          sender.sendMessage(TextFormat.RED + "Usage: " + this.USAGE);

        }
        else
        {

          if(args[0].equalsIgnoreCase("version"))
          {

            sender.sendMessage(TextFormat.YELLOW + "-- WhitelistReason version --");

            sender.sendMessage(TextFormat.GREEN + this.VERSION);

          }
          else if(args[0].equalsIgnoreCase("add"))
          {

            if(args.length == 1)
            {

              sender.sendMessage(TextFormat.RED + "Usage: /whitelistreason add <player>");

            }
            else
            {

              String name = args[1];

              List players = this.config.getList("players");

              String[] wPlayers = this.toS(players);

              if(this.in_array(name, wPlayers))
              {

                sender.sendMessage(TextFormat.RED + name + " is already whitelisted.");

              }
              else
              {

                String[] p = Arrays.copyOf(wPlayers, wPlayers.length + 1);

                p[p.length - 1] = name;

                this.config.set("players", p);

                this.config.save();

                sender.sendMessage(TextFormat.GREEN + "Successfully updated and added " + name + " to the whitelist.");

              }

            }

          }
          else if(args[0].equalsIgnoreCase("set"))
          {

            if(args.length == 1)
            {

              sender.sendMessage(TextFormat.RED + "Usage: /whitelistreason set <reason>");

            }
            else
            {

              args[0] = "";

              String reason = this.implode(" ", args);

              this.config.set("reason", reason);

              this.config.save();

              sender.sendMessage(TextFormat.GREEN + "Successfully updated the whitelist reason.");

            }

          }
          else if(args[0].equalsIgnoreCase("enable"))
          {

            this.config.set("whitelisted", true);

            this.config.save();

            sender.sendMessage(TextFormat.GREEN + "Successfully enabled the whitelist.");

          }
          else if(args[0].equalsIgnoreCase("disable"))
          {

            this.config.set("whitelisted", false);

            this.config.save();

            sender.sendMessage(TextFormat.GREEN + "Successfully disabled the whitelist.");

          }

        }

      break;

    }

    return true;

  }

  @Override

  public void onDisable()
  {

    this.getLogger().info(TextFormat.RED + "Disabled.");

  }

}
