package haroldolivieri.candidateslist.feature

import dagger.Module
import dagger.Provides

@Module
class CandidateListModule {
    @Provides
    fun provideView(activity: MainActivity) : CandidateListContract.View = activity

    @Provides
    fun providePresenter(presenter: CandidateListPresenter)
            : CandidateListContract.Presenter = presenter
}