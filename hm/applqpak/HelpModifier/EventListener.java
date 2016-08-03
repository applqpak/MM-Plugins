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

    String command = event.getMessage().replace("/", "");

    Player player = event.getPlayer();

    List list = this.plugin.config.getList("page_1");

    List list2 = this.plugin.config.getList("page_2");

    List list3 = this.plugin.config.getList("page_3");

    List list4 = this.plugin.config.getList("page_4");

    List list5 = this.plugin.config.getList("page_5");

    List list6 = this.plugin.config.getList("page_6");

    String[] page_1 = this.plugin.toS(list);

    String[] page_2 = this.plugin.toS(list2);

    String[] page_3 = this.plugin.toS(list3);

    String[] page_4 = this.plugin.toS(list4);

    String[] page_5 = this.plugin.toS(list5);

    String[] page_6 = this.plugin.toS(list6);

    switch(command.toLowerCase())
    {

      case "help":

      case "help 1":

        for(int i = 0; i < 7; i++)
        {

          player.sendMessage(page_1[i]);

        }

        event.setCancelled();

      break;

      case "help 2":

        for(int i = 0; i < 7; i++)
        {

          player.sendMessage(page_2[i]);

        }

        event.setCancelled();

      break;

      case "help 3":

        for(int i = 0; i < 7; i++)
        {

          player.sendMessage(page_3[i]);

        }

        event.setCancelled();

      break;

      case "help 4":

        for(int i = 0; i < 7; i++)
        {

          player.sendMessage(page_4[i]);

        }

        event.setCancelled();

      break;

      case "help 5":

        for(int i = 0; i < 7; i++)
        {

          player.sendMessage(page_5[i]);

        }

        event.setCancelled();

      break;

      case "help 6":

        for(int i = 0; i < 7; i++)
        {

          player.sendMessage(page_6[i]);

        }

        event.setCancelled();

      break;

    }

  }

}
