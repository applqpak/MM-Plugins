package applqpak.UserBio;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Config;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.Command;
import cn.nukkit.Player;

import java.io.File;

public class Main extends PluginBase
{

  public String VERSION = "v1.0.0";

  public String USAGE = "/bio <version | set | see> <message | player>";

  public Config config;

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

  @Override

  public void onEnable()
  {

    this.getDataFolder().mkdirs();

    if(!(new File(this.getDataFolder(), "config.yml").exists()))
    {

      saveResource("config.yml");

    }

    this.config = getConfig();

    this.getLogger().info(TextFormat.GREEN + "Enabled");

  }

  @Override

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {

    switch(cmd.getName().toLowerCase())
    {

      case "bio":

        if(args.length == 0)
        {

          sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.USAGE);

        }
        else
        {

          if(args[0].equalsIgnoreCase("version"))
          {

            sender.sendMessage(TextFormat.YELLOW + "-- UserBio version --");

            sender.sendMessage(TextFormat.GREEN + this.VERSION);

          }
          else if(args[0].equalsIgnoreCase("set"))
          {

            if(args.length == 1)
            {

              sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.USAGE);

            }
            else
            {

              args[0] = "";

              String message = this.implode(" ", args);

              this.config.set(sender.getName().toLowerCase(), message);

              this.config.save();

              sender.sendMessage(TextFormat.GREEN + "Sucessfully set your bio!");

            }

          }
          else if(args[0].equalsIgnoreCase("see"))
          {

            if(args.length == 1)
            {

              sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.USAGE);

            }
            else
            {

              if(this.config.exists(args[1].toLowerCase()))
              {

                String bio = String.valueOf(this.config.get(args[1].toLowerCase()));

                sender.sendMessage(TextFormat.YELLOW + "-- " + args[1] + "'s bio --");

                sender.sendMessage(TextFormat.GREEN + bio);

              }
              else
              {

                sender.sendMessage(TextFormat.RED + args[1] + " doesn't have a bio.");

              }

            }

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
