package haroldolivieri.candidateslist.feature.list

import haroldolivieri.candidateslist.domain.Candidate
import haroldolivieri.candidateslist.repository.CandidatesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CandidateListPresenter
@Inject constructor(private val view: CandidateListContract.View,
                    private val repository: CandidatesRepository)
    : CandidateListContract.Presenter {

    override fun fetchData() {
        repository.fetchCandidates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.showList(it) },
                        { t -> view.showError(t.message ?: "Unexpected error") })
    }

    override fun deleteCandidate(candidate: Candidate) {
        repository.deleteCandidate(candidate)
    }
}