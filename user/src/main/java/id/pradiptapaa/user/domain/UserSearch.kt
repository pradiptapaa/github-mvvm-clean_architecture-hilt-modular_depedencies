package id.pradiptapaa.user.domain

data class UserSearch(
    val incomplete_results: Boolean? = false,
    val items: List<User>? = listOf(),
    val total_count: Int? = 0
)