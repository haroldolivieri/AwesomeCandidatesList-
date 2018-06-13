package haroldolivieri.candidateslist.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(CandidateEntity::class)], version = 1)
abstract class CandidateDatabase : RoomDatabase() {
    abstract fun candidateDAO(): CandidateDAO
}