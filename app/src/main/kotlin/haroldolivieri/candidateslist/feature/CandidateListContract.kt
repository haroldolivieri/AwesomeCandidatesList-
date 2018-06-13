package haroldolivieri.candidateslist.feature

import haroldolivieri.candidateslist.Candidate


object CandidateListContract {
    interface View {
        fun showList(candidates: List<Candidate>)
        fun showError(message : String)
    }

    interface Presenter {
        fun onCreate()
    }
}