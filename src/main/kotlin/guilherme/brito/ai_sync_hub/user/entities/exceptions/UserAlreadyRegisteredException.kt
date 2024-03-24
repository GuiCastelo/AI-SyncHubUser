package guilherme.brito.ai_sync_hub.user.entities.exceptions

import java.lang.RuntimeException

class UserAlreadyRegisteredException(
    val code: String = "user.already.registered",
    override val message: String
) : RuntimeException(message)