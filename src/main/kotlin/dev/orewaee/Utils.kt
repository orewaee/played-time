package dev.orewaee

import java.util.concurrent.TimeUnit

class Utils {
    companion object {
        fun ticksToTime(ticks: Long): String {
            val target = ticks / 20

            val days = TimeUnit.SECONDS.toDays(target)
            val hours = TimeUnit.SECONDS.toHours(target) - TimeUnit.DAYS.toHours(days)
            val minutes = TimeUnit.SECONDS.toMinutes(target) - TimeUnit.HOURS.toMinutes(hours) - TimeUnit.DAYS.toMinutes(days)
            val seconds = TimeUnit.SECONDS.toSeconds(target) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.DAYS.toSeconds(days)

            var result = ""

            if (days > 0) result += " ${days}d"
            if (hours > 0) result += " ${hours}h"
            if (minutes > 0) result += " ${minutes}m"
            if (seconds > 0) result += " ${seconds}s"

            return result
        }
    }
}
