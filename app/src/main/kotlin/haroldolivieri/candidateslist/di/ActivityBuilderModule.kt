package haroldolivieri.candidateslist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import haroldolivieri.candidateslist.feature.CandidateListModule
import haroldolivieri.candidateslist.feature.MainActivity
import haroldolivieri.candidateslist.repository.local.LocalModule

/**
 * Working with ActivityBuilderModule on dagger-android is a better way to work with
 * scoped subComponents to ensure the encapsulation
 */

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [LocalModule::class, CandidateListModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}