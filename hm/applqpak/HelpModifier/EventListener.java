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

    List p1 = this.plugin.config.getList("page_1");

    String[] page_1 = this.plugin.toS(p1);

    Player player = event.getPlayer();

    if(command[0] == "help" || command[0] == "/help" || command[0] == "/?")
    {

      for(int i = 0; i <= 7; i++)
      {

        player.sendMessage(page_1[i]);

      }

      event.setCancelled();

    }

  }

}
