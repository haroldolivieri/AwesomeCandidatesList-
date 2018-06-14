package haroldolivieri.candidateslist.repository

import haroldolivieri.candidateslist.domain.Candidate
import haroldolivieri.candidateslist.repository.local.CandidateDAO
import haroldolivieri.candidateslist.repository.local.toEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CandidatesRepositoryImpl @Inject constructor(private val candidateDAO: CandidateDAO)
    : CandidatesRepository {

    override fun fetchCandidates(): Single<List<Candidate>> =
            candidateDAO.fetchCandidates()
                    .map { it.map { it as Candidate } }

    override fun saveCandidate(candidate: Candidate): Completable =
            candidateDAO.upsertCompletable(candidate.toEntity())

    override fun deleteCandidate(candidate: Candidate) {
        candidateDAO.deleteCompletable(candidate.toEntity())
                .subscribeOn(Schedulers.io()).subscribe()
    }
}