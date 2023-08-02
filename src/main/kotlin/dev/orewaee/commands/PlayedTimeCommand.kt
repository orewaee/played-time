package dev.orewaee.commands

import org.bukkit.Statistic
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor

import dev.orewaee.Utils

class PlayedTimeCommand: CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Component.text("This command is only available to players"))

            return true
        }

        val server = sender.server

        val statistics = mutableMapOf<String, Int>()

        for (player in server.onlinePlayers + server.offlinePlayers) {
            val name = player.name ?: ""

            statistics[name] = player.getStatistic(Statistic.PLAY_ONE_MINUTE)
        }

        val sortedStatistics = statistics.toList().sortedBy { (_, value) -> value }.toMap()

        val title = Component
            .text("Top by played time:\n")
            .color(TextColor.color(0x09f391))

        sender.sendMessage(title)

        var index = 1
        for (item in sortedStatistics) {
            val (name, ticks) = item

            val row = Component
                .text("$index. ")
                .append(
                    Component
                        .text(name)
                        .color(TextColor.color(0x09f391))
                )
                .append(
                    Component
                        .text(" - ${Utils.ticksToTime(ticks.toLong())}")
                )

            sender.sendMessage(row)

            if (index == 10) break
            else index++
        }

        return true
    }
}