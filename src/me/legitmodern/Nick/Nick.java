package me.legitmodern.Nick;

import me.legitmodern.Nick.TagApiListener;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.TagAPI;
 
public class Nick extends JavaPlugin implements Listener {
	
	public static Nick plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
       
        public void onEnable() {
        	
            	Bukkit.getServer().getPluginManager().registerEvents(this, this);
                
                PluginDescriptionFile pdfFile = this.getDescription();
        		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled.");

                for (Plugin p : getServer().getPluginManager().getPlugins()) {
                    String name = p.getClass().getName();
                    
                    if (name.equals("org.kitteh.tag.TagAPI")) {
                    	
                    Bukkit.getServer().getPluginManager().registerEvents(new TagApiListener(this), this);
                	this.logger.info("[Nick] TagAPI has been located in plugin directory.");
                	this.logger.info("[Nick] TagAPI will now be used for nickname changes!");
                	
                	PluginDescriptionFile pdfFile2 = this.getDescription();
            		this.logger.info(pdfFile2.getName() + " version " + pdfFile2.getVersion() + " has been enabled.");
              }
           }
        }
        
        public void onDisable() {
        	PluginDescriptionFile pdfFile = this.getDescription();
    		this.logger.info(pdfFile.getName() + " has been disabled.");
        }
        
        ////// COMMANDS //////
        ////// COMMANDS //////
        ////// COMMANDS //////
 
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
               
        		
        		/*
                if (!(sender instanceof Player)) {
                        sender.sendMessage(ChatColor.RED + "You cannot execute this command from the console!");
                        return true;
                }
                */
               
                // COMMAND: /NICK //
        		// COMMAND: /NICK //
        		// COMMAND: /NICK //
                
                if (cmd.getName().equalsIgnoreCase("nick")) {
                	
                    if ((sender instanceof Player)) {
                      Player p = (Player) sender;
                      
                      if (args.length == 0) {
                        p.sendMessage(ChatColor.DARK_RED + "You did not a specify a nickname!");
                        return true;
                      }
                      
                      if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("off")) {
                        	if (p.hasPermission("nick.off")) {
                          p.setDisplayName(p.getName());
                          p.setPlayerListName(p.getName());
                          TagAPI.refreshPlayer(p);
                          p.sendMessage(ChatColor.GRAY + "Nickname successfully removed!");
                          return true;
                       }
                        	else {
                        		p.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command.");
                        	}
                   }
                        if (args[0].equalsIgnoreCase("help")) {
                        	if (p.hasPermission("nick.help")) {
                                p.sendMessage(ChatColor.RED + "---- Nick: Programmed by LegitModern & duker02 ----");
                                p.sendMessage(ChatColor.AQUA + "/nick (nickname): " + ChatColor.GOLD + "Gives yourself a nickname!");
                                p.sendMessage(ChatColor.AQUA + "/nickname help: " + ChatColor.GOLD + "Displays help panel which lists Nick commands!");
                                p.sendMessage(ChatColor.AQUA + "/nickname reload: " + ChatColor.GOLD + "Reloads all Nick files!");
                                p.sendMessage(ChatColor.AQUA + "/nickname off: " + ChatColor.GOLD + "Turns off your nickname!");
                                p.sendMessage(ChatColor.WHITE + "/nickname disable: " + ChatColor.GOLD + "Disables Nick plugin!");
                                return true;
                        	}
                        	else {
                        		p.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command.");
                        	}
                        }
                        	if (args[0].equalsIgnoreCase("reload")) {
                        		if (p.hasPermission("nick.admin.reload")) {
                        			 for (String key : getConfig().getKeys(false)) {
                                         getConfig().set(key, null);
                                         this.saveConfig();
                                     }
                        			 
                        	  for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                              pl.setDisplayName(pl.getName());
                              pl.setPlayerListName(pl.getName());
                              TagAPI.refreshPlayer(pl);
                              p.sendMessage(ChatColor.AQUA + "Nick files have been reloaded, online player names have been reset.");
                        	}
                        }
                        		else {
                        			p.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command.");
                        		}
                    }
                        	
                        	
                        	
                        	if (args[0].equalsIgnoreCase("disable")) {
                        		if (p.hasPermission("nick.admin.disable")) {
                                    this.logger.info("[Nick] I have been disabled by " + sender.getName() + "!");
                                    p.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.DARK_RED + "Plugin disabling!");
                                    this.getServer().getPluginManager().disablePlugin(plugin);
                                    return true;
                        		}
                        	}
                        	
                       if (p.hasPermission("nicknames.nick.self")) {
                        	
                        	 String nick = "";
                             for (String arg : args) {
                                     nick += arg + " ";
                          }
                            
                         nick = nick.substring(0, nick.length() - 1);
                             
                          if (p.hasPermission("nick.color")) {
                              nick = nick.replaceAll("&", "§");
                         }
                            else {
                                 p.sendMessage(ChatColor.RED + "You do not have permission to use color codes.");
                                 return true;
                          }
                          
                          p.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.AQUA + "You have changed your nickname to: " + nick);
                          p.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.RED + "Nicknames will be logged! Please chat to enable your nickname.");
                          this.getConfig().set(p.getDisplayName(), nick);
                          this.saveConfig();
                          return true;
                       }
                        p.sendMessage(ChatColor.DARK_RED + "You don't have permission to change your nickname!");
                        return true;
                     }
                      
                      if (args.length == 2) {
                          if (p.hasPermission("nicknames.nick.other")) {
                            Player target = Bukkit.getPlayer(args[0]);
                            
                            if (target != null) {
                            	
                            	 String nick = "";
                                 for (String arg : args) {
                                         nick += arg + " ";
                               }
                                
                             nick = nick.substring(0, nick.length() - 1);
                                 
                              if (p.hasPermission("nick.color")) {
                                  nick = nick.replaceAll("&", "§");
                             }
                                else {
                                     p.sendMessage(ChatColor.DARK_RED + "You do not have permission to use color codes.");
                                     return true;
                              }
                              
                              target.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.AQUA + target + " has changed your nickname to: " + nick);
                              target.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.RED + "Please chat to enable your nickname.");
                              p.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.AQUA + " You have changed" + target + "'s nickname to: " + nick);
                              p.sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.RED + "Nicknames will be logged!");
                              this.getConfig().set(target.getDisplayName(), nick);
                              this.saveConfig();
                              return true;
                            }
                            p.sendMessage(ChatColor.RED + "Player: " + args[0] + " is not currently online.");
                            return true;
                          }

                          p.sendMessage(ChatColor.DARK_RED + "You don't have permission to change other's nicknames.");
                          return true;
                        }

                        return true;
                      }
                    
                    if ((sender instanceof ConsoleCommandSender)) {
                        this.logger.warning("[Nick] You cannot execute this command from the console!");
                        return true;
                   }
                      return true;
                 }
                	return true;
                }
       
        ////// EVENTS ///////
        ////// EVENTS ///////
        ////// EVENTS ///////
        
        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent e) {
                if (this.getConfig().getString(e.getPlayer().getDisplayName()) != null) {
                        e.getPlayer().setDisplayName(this.getConfig().getString(e.getPlayer().getDisplayName()) + ChatColor.RESET);
                        e.getPlayer().setPlayerListName(this.getConfig().getString(e.getPlayer().getDisplayName()) + ChatColor.RESET);
                        TagAPI.refreshPlayer(e.getPlayer());
                        
                        } else if (this.getConfig().getString(e.getPlayer().getDisplayName()).length() > 14) {
                            //String name = this.getConfig().getString(e.getPlayer().getName()).substring(0, 14) + ChatColor.RESET;
                            e.getPlayer().setDisplayName(this.getConfig().getString(e.getPlayer().getName()).substring(0, 14) + ChatColor.RESET);
                            e.getPlayer().setPlayerListName(this.getConfig().getString(e.getPlayer().getName()).substring(0, 14) + ChatColor.RESET);
                            TagAPI.refreshPlayer(e.getPlayer());
                            e.getPlayer().sendMessage(ChatColor.BLUE + "[Nick] " + ChatColor.RED + "You entered a nickname longer than 14 characters. Your player list (tab) name has been set to the first 14 characters of your new nickname!");
                        }
                	}
        
       @EventHandler
       public void onJoinEvent(PlayerJoinEvent e) {
    	   if (this.getConfig().getString(e.getPlayer().getName()) != null) {
    			   e.setJoinMessage(e.getJoinMessage().replaceAll(e.getPlayer().getName(), this.getConfig().getString(e.getPlayer().getDisplayName()) + ChatColor.YELLOW));
    		   }
      }
       
       @EventHandler
       public void onLeaveEvent(PlayerQuitEvent e) {
    	   if (this.getConfig().getString(e.getPlayer().getName()) != null) {
    			   e.setQuitMessage(e.getQuitMessage().replaceAll(e.getPlayer().getName(), this.getConfig().getString(e.getPlayer().getDisplayName()) + ChatColor.YELLOW));
    	   }
      }
       
       @EventHandler
       public void onKickEvent(PlayerKickEvent e) {
    	   if (this.getConfig().getString(e.getPlayer().getName()) != null) {
    			   e.setLeaveMessage(e.getLeaveMessage().replaceAll(e.getPlayer().getName(), this.getConfig().getString(e.getPlayer().getDisplayName()) + ChatColor.YELLOW));
    	   }
      }
}