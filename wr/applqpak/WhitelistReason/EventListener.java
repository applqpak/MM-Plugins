package applqpak.WhitelistReason;

import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerPreLoginEvent;

import java.util.*;

public class EventListener implements Listener
{

  public Main plugin;

  public EventListener(Main plugin)
  {

    this.plugin = plugin;

  }

  @EventHandler

  public void onPlayerPreLogin(PlayerPreLoginEvent event)
  {

    boolean isWhitelisted = this.plugin.config.getBoolean("whitelisted");

    String reason = this.plugin.config.getString("reason");

    List players = this.plugin.config.getList("players");

    String[] wPlayers = this.plugin.toS(players);

    if(isWhitelisted == true)
    {

      if(!(this.plugin.in_array(event.getPlayer().getName().toLowerCase(), wPlayers)))
      {

        event.getPlayer().kick(reason, false);

        event.setCancelled(true);

      }

    }

  }

}
