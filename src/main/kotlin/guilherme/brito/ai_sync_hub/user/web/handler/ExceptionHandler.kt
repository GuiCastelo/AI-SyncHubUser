package guilherme.brito.ai_sync_hub.user.web.handler

import guilherme.brito.ai_sync_hub.user.entities.exceptions.ApiError
import guilherme.brito.ai_sync_hub.user.entities.exceptions.UserAlreadyRegisteredException
import guilherme.brito.ai_sync_hub.user.entities.exceptions.UserNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@RestControllerAdvice
class ExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler(value = [UserNotFoundException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFound(ex: UserNotFoundException): ApiError {
        logger.error(ex.message, ex)
        return ApiError(
            code = ex.code,
            message = ex.message
        )
    }

    @ExceptionHandler(value = [UserAlreadyRegisteredException::class])
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUserAlreadyRegistered(ex: UserAlreadyRegisteredException): ApiError {
        logger.error(ex.message, ex)
        return ApiError(
            code = ex.code,
            message = ex.message
        )
    }

    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception): ApiError {
        logger.error(ex.message, ex)
        return ApiError(
            code = "server.error",
            message = ex.message
        )
    }
}