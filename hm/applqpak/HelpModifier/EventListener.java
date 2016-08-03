package applqpak.HelpModifier;

import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.Player;

import java.util.*;

public class EventListener implements Listener
{

  public Main plugin;

  public EventListener(Main plugin)
  {

    this.plugin = plugin;

  }

  @EventHandler

  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
  {

    String[] command = event.getMessage().split(" ");

    Player player = event.getPlayer();

    List list = this.plugin.config.getList("page_1");

    String[] page_1 = this.plugin.toS(list);

    this.plugin.getLogger().info(page_1[0]);

  }

}
