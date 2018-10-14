package software.rst.taran

import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.actor

class TaranServiceActor(val taranService: TaranService) {

    val inbox = GlobalScope.actor<Command> {
        for (msg in channel) {
            handle(msg)
        }
    }

    private fun handle(msg: Command) {
        when (msg) {
            is Command.OpenGate -> taranService.openGate(msg.id)
        }
    }

    sealed class Command {
        data class OpenGate(val id: String) : Command()
    }
}
