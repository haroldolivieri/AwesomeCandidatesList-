package haroldolivieri.candidateslist.repository

import haroldolivieri.candidateslist.domain.Candidate
import io.reactivex.Completable
import io.reactivex.Single


interface CandidatesRepository {
    fun fetchCandidates() : Single<List<Candidate>>
    fun saveCandidate(candidate: Candidate): Completable
    fun deleteCandidate(candidate: Candidate)
}