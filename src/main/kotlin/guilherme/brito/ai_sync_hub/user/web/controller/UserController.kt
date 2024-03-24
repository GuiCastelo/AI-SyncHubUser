package guilherme.brito.ai_sync_hub.user.web.controller

import guilherme.brito.ai_sync_hub.user.entities.dto.UserRequestDTO
import guilherme.brito.ai_sync_hub.user.entities.dto.UserResponseDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "User Controller", description = "Responsável pelo gerenciamento de usuários da aplicação")
interface UserController {

    @Operation(
        summary = "Get all users",
        description = "Get information about all users registered in the system"
    )
    fun getUsers(): List<UserResponseDTO>

    @Operation(
        summary = "Get all users",
        description = "Get information about all users registered in the system",
        parameters = [
            Parameter(
                name = "id",
                required = true,
                `in` = ParameterIn.PATH,
                example = "3"
            )
        ]
    )
    fun getUserById(id: Long): UserResponseDTO

    @Operation(
        summary = "Create user",
        description = "Add a new user passing the necessary information"
    )
    fun createUser(user: UserRequestDTO): UserResponseDTO

    @Operation(
        summary = "Update user",
        description = "Update information about a user",
        parameters = [
            Parameter(
                name = "id",
                required = true,
                `in` = ParameterIn.PATH,
                example = "3"
            )
        ]
    )
    fun updateUserById(id: Long, editUser: UserRequestDTO): UserResponseDTO

    @Operation(
        summary = "Delete user",
        description = "Delete a user from system",
        parameters = [
            Parameter(
                name = "id",
                required = true,
                `in` = ParameterIn.PATH,
                example = "3"
            )
        ]
    )
    fun deleteById(id: Long)
}