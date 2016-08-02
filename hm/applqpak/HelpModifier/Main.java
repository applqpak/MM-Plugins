package applqpak.HelpModifier;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;

public class Main extends PluginBase
{

  public Config config;

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

  public void onDisable()
  {

    this.getLogger().info(TextFormat.RED + "Disabled.");

  }

}
