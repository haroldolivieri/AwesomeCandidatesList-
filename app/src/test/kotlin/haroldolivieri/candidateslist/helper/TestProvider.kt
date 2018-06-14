package haroldolivieri.candidateslist.helper

import haroldolivieri.candidateslist.repository.local.CandidateEntity
import org.mockito.Mockito

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}
private fun <T> uninitialized(): T = null as T

val candidates = listOf(CandidateEntity(name = "Haroldo",
        email = "olivierisoares@gmail.com", phoneNumber = "+48 504 203 260", assessment = "A"),
        CandidateEntity(name = "John Doe", email = "johnDoe@gmail.com",
                phoneNumber = "", assessment = "F"),
        CandidateEntity(name = "Marcos", email = "marcos@gmail.com",
                phoneNumber = "+48 504 243 260", assessment = "C"))
