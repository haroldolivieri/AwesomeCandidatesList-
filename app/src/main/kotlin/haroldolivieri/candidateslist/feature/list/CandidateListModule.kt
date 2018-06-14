package haroldolivieri.candidateslist.feature.list

import dagger.Module
import dagger.Provides
import haroldolivieri.candidateslist.repository.CandidatesRepository
import haroldolivieri.candidateslist.repository.CandidatesRepositoryImpl

@Module
class CandidateListModule {
    @Provides
    fun provideView(activity: CandidateListActivity)
            : CandidateListContract.View = activity

    @Provides
    fun providePresenter(presenter: CandidateListPresenter)
            : CandidateListContract.Presenter = presenter

    @Provides
    fun provideRepository(candidatesRepository: CandidatesRepositoryImpl)
            : CandidatesRepository = candidatesRepository
}