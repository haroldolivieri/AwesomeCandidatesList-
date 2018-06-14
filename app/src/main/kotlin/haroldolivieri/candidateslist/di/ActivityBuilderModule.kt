package haroldolivieri.candidateslist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import haroldolivieri.candidateslist.feature.list.CandidateListModule
import haroldolivieri.candidateslist.feature.createoredit.CreateOrEditActivity
import haroldolivieri.candidateslist.feature.createoredit.CreateOrEditModule
import haroldolivieri.candidateslist.feature.list.CandidateListActivity
import haroldolivieri.candidateslist.repository.local.LocalModule

/**
 * Working with ActivityBuilderModule on dagger-android is a better way to work with
 * scoped subComponents to ensure the encapsulation
 */

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [LocalModule::class, CandidateListModule::class])
    internal abstract fun bindListActivity(): CandidateListActivity

    @ContributesAndroidInjector(modules = [LocalModule::class, CreateOrEditModule::class])
    internal abstract fun bindCreateOrEditActivity(): CreateOrEditActivity
}