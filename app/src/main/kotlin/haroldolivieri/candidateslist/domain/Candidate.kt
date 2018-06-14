package haroldolivieri.candidateslist.domain

import java.io.Serializable

interface Candidate : Serializable {
    val name : String
    val email : String
    val phoneNumber : String
    val assessmentGrade : AssessmentGrade
}

enum class AssessmentGrade {
    A, B, C, D, E, F
}