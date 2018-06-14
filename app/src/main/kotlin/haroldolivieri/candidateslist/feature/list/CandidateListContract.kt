package haroldolivieri.candidateslist.feature.list

import haroldolivieri.candidateslist.domain.Candidate


object CandidateListContract {
    interface View {
        fun showList(candidates: List<Candidate>)
        fun showError(message : String)
    }

    interface Presenter {
        fun fetchData()
        fun deleteCandidate(candidate: Candidate)
    }
}