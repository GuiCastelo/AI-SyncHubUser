package guilherme.brito.ai_sync_hub.user.entities.exceptions

class ApiError(
    val code: String = "server.error",
    val message: String? = ""
)