package haroldolivieri.candidateslist

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import haroldolivieri.candidateslist.di.ApplicationComponent
import haroldolivieri.candidateslist.di.DaggerApplicationComponent


class CandidatesApp : DaggerApplication() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build()

        applicationComponent.inject(this)
        return applicationComponent
    }
}