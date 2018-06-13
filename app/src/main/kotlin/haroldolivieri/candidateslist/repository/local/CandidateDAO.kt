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

    @Insert
    abstract fun insert(candidateEntity: CandidateEntity)

    @Delete
    abstract fun delete(candidateEntity: CandidateEntity)

    @Update
    abstract fun update(candidateEntity: CandidateEntity)

    open fun insertCompletable(candidateEntity: CandidateEntity): Completable {
        return Completable.fromAction { insert(candidateEntity) }
    }

    open fun deleteCompletable(candidateEntity: CandidateEntity): Completable {
        return Completable.fromAction { delete(candidateEntity) }
    }

    open fun updateCompletable(candidateEntity: CandidateEntity): Completable {
        return Completable.fromAction { update(candidateEntity) }
    }
}