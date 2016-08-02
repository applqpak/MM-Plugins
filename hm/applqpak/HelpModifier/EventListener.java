package applqpak.HelpModifier;

import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;

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

  }

}
