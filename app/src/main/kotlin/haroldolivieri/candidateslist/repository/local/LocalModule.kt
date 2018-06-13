package haroldolivieri.candidateslist.repository.local

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class LocalModule {
    @Provides
    fun provideDatabase(context : Context) : CandidateDatabase =
        Room.databaseBuilder(context,
                CandidateDatabase::class.java, "CandidateList_DB").build()

    @Provides
    fun provideCandidateDAO(roomDatabase: CandidateDatabase) : CandidateDAO =
            roomDatabase.candidateDAO()
}