package haroldolivieri.candidateslist.repository.local

import android.arch.persistence.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class CandidateDAO {
    companion object {
        const val CANDIDATE_TABLE_NAME = "candidate"
    }

    @Query("SELECT * FROM $CANDIDATE_TABLE_NAME ORDER BY name ASC")
    abstract fun fetchCandidates(): Single<List<CandidateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(candidateEntity: CandidateEntity)

    @Delete
    abstract fun delete(candidateEntity: CandidateEntity)

    open fun upsertCompletable(candidateEntity: CandidateEntity): Completable {
        return Completable.fromAction { upsert(candidateEntity) }
    }

    open fun deleteCompletable(candidateEntity: CandidateEntity): Completable {
        return Completable.fromAction { delete(candidateEntity) }
    }
}