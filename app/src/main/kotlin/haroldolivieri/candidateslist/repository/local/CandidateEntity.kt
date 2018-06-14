package haroldolivieri.candidateslist.repository.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import haroldolivieri.candidateslist.domain.AssessmentGrade
import haroldolivieri.candidateslist.domain.Candidate
import haroldolivieri.candidateslist.repository.local.CandidateDAO.Companion.CANDIDATE_TABLE_NAME

@Entity(tableName = CANDIDATE_TABLE_NAME)
data class CandidateEntity(override val name: String,
                           @PrimaryKey
                           override val email: String,
                           override val phoneNumber: String,
                           val assessment: String) : Candidate {

    override val assessmentGrade: AssessmentGrade
        get() = AssessmentGrade.valueOf(assessment)
}

fun Candidate.toEntity(): CandidateEntity =
        CandidateEntity(name = name, email = email,
                phoneNumber = phoneNumber, assessment = assessmentGrade.name)