package haroldolivieri.candidateslist.feature.createoredit

import dagger.Module
import dagger.Provides
import haroldolivieri.candidateslist.repository.CandidatesRepository
import haroldolivieri.candidateslist.repository.CandidatesRepositoryImpl


@Module
class CreateOrEditModule {
    @Provides
    fun provideView(activity: CreateOrEditActivity)
            : CreateOrEditContract.View = activity

    @Provides
    fun providePresenter(presenter: CreateOrEditPresenter)
            : CreateOrEditContract.Presenter = presenter

    @Provides
    fun provideRepository(candidatesRepository: CandidatesRepositoryImpl)
            : CandidatesRepository = candidatesRepository
}