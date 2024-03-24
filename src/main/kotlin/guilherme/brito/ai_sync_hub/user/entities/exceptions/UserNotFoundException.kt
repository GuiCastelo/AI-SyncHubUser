package guilherme.brito.ai_sync_hub.user.entities.exceptions

import java.lang.RuntimeException

class UserNotFoundException(
    val code: String = "user.not.found",
    override val message: String
): RuntimeException(message)