package applqpak.WarnPlayer;

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
  
  public String WARN_USAGE = "/warn <version | player> < reason >";
  
  public String WARNS_USAGE = "/warns <player>";
  
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
    
    this.getLogger().info(TextFormat.GREEN + "Enabled.");
  
  }
  
  @Override
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
  
    switch(cmd.getName().toLowerCase())
    {
    
      case "warn":
      
        if(args.length == 0)
        {
        
          sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.WARN_USAGE);
        
        }
        else
        {
        
          if(args[0].equalsIgnoreCase("version"))
          {
          
            sender.sendMessage(TextFormat.YELLOW + "-- WarnPlayer version --");
            
            sender.sendMessage(TextFormat.GREEN + this.VERSION);
          
          }
          else
          {
          
            String name = args[0];
            
            if(args.length == 1)
            {
            
              sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.WARN_USAGE);
            
            }
            else
            {
            
              args[0] = "";
              
              String reason = this.implode(" ", args);
              
              if(this.getServer().getPlayer(name) == null)
              {
              
                sender.sendMessage(TextFormat.RED + name + " isn't online.");
              
              }
              else
              {
              
                Player player = this.getServer().getPlayer(name);
                
                String pName = player.getName();
                
                player.sendMessage(TextFormat.RED + "You have been warned by " + sender.getName() + " for " + reason);
                
                this.getServer().broadcastMessage(TextFormat.YELLOW + pName + " has been warned by " + sender.getName() + " for " + reason);
                
                if(this.config.exists(pName.toLowerCase()))
                {
                
                  int warnCount = this.config.getInt(pName.toLowerCase());
                  
                  this.config.set(pName.toLowerCase(), warnCount + 1);
                
                }
                else
                {
                
                  this.config.set(pName.toLowerCase(), 1);
                
                }
              
              }
            
            }
          
          }
        
        }
      
      break;
      
      case "warns":
      
        if(args.length == 0)
        {
        
          sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.WARNS_USAGE);
        
        }
        else
        {
        
          String name = args[0];
          
          if(this.getServer().getPlayer(name) == null)
          {
          
            sender.sendMessage(TextFormat.RED + name + " isn't online.");
          
          }
          else
          {
          
            Player player = this.getServer().getPlayer(name);
            
            String pName = player.getName();
            
            if(this.config.exists(pName.toLowerCase()))
            {
            
              int warnCount = this.config.getInt(pName.toLowerCase());
              
              sender.sendMessage(TextFormat.GREEN + pName + " has " + String.valueOf(warnCount) + " warns.");
            
            }
            else
            {
            
              sender.sendMessage(TextFormat.RED + pName + " doesn't have any warns.");
            
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
