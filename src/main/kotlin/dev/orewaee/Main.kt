package dev.orewaee

import org.bukkit.plugin.java.JavaPlugin

import dev.orewaee.commands.PlayedTimeCommand

class Main: JavaPlugin() {
    override fun onEnable() {
        registerCommands()
    }
    
    private fun registerCommands() {
        getCommand("playedtime")?.setExecutor(PlayedTimeCommand())
    }
}
