package haroldolivieri.candidateslist.feature.createoredit

import haroldolivieri.candidateslist.repository.CandidatesRepository
import haroldolivieri.candidateslist.repository.local.CandidateEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateOrEditPresenter
@Inject constructor(private val view: CreateOrEditContract.View,
                    private val repository: CandidatesRepository)
    : CreateOrEditContract.Presenter {

    override fun save(email: String, name: String, phone: String, grade: String) {
        repository.saveCandidate(CandidateEntity(name, email, phone, grade))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onSuccess() }, { t -> t.message?.let { view.showError(it) } })
    }
}