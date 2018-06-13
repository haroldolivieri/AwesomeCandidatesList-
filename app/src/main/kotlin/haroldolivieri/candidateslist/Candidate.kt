package haroldolivieri.candidateslist


interface Candidate {
    val name : String
    val email : String
    val phoneNumber : String
    val assessmentGrade : AssessmentGrade
}

enum class AssessmentGrade {
    A, B, C, D, E, F
}