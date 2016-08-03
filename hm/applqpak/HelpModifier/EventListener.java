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

    List page_1 = this.plugin.config.getList("page_1");

    String[] page_1 = this.plugin.toS(page_1);

    Player player = event.getPlayer();

    if(command[0] == "/help" || command[0] == "/?")
    {

      for(int i = 1; i <= 8; i++)
      {

        player.sendMessage(page_1["message_" + String.valueOf(i)]);

      }

      event.setCancelled();

    }

  }

}
