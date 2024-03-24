package guilherme.brito.ai_sync_hub.user.web.controller.impl

import guilherme.brito.ai_sync_hub.user.entities.dto.UserRequestDTO
import guilherme.brito.ai_sync_hub.user.entities.dto.UserResponseDTO
import guilherme.brito.ai_sync_hub.user.usecases.service.UserService
import guilherme.brito.ai_sync_hub.user.web.controller.UserController
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class UserControllerImpl(
    private val userService: UserService
) : UserController {
    @GetMapping("/v1/users")
    override fun getUsers(): List<UserResponseDTO> =
        userService.getUsers()

    @GetMapping("/v1/user/{id}")
    override fun getUserById(@PathVariable id: Long): UserResponseDTO =
        userService.getUser(id)

    @PostMapping("/v1/user")
    @ResponseStatus(HttpStatus.CREATED)
    override fun createUser(@RequestBody user: UserRequestDTO): UserResponseDTO =
        userService.createUser(user)

    @PutMapping("/v1/user/{id}")
    override fun updateUserById(
        @PathVariable id: Long,
        @RequestBody editUser: UserRequestDTO
    ): UserResponseDTO =
        userService.updateUser(id, editUser)

    @DeleteMapping("/v1/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteById(@PathVariable id: Long) =
        userService.deleteUser(id)
}