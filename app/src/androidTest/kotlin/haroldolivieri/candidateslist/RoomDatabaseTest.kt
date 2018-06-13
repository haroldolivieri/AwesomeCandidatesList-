package haroldolivieri.candidateslist

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import haroldolivieri.candidateslist.repository.local.CandidateDAO
import haroldolivieri.candidateslist.repository.local.CandidateDatabase
import haroldolivieri.candidateslist.repository.local.CandidateEntity
import haroldolivieri.candidateslist.repository.local.toEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {

    private lateinit var candidateDatabase: CandidateDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        candidateDatabase = Room.inMemoryDatabaseBuilder(context,
                CandidateDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        candidateDatabase.close()
    }

    @Test
    fun writeCandidateAndReadInList() {
        val candidate : Candidate = CandidateEntity(name = "Haroldo",
                email = "olivierisoares@gmail.com",
                phoneNumber = "+48 504 203 260", assessment = "A")

        val testObserverInsert = candidateDatabase.candidateDAO()
                .insertCompletable(candidate.toEntity()).test()

        testObserverInsert.assertComplete()
        testObserverInsert.assertNoErrors()

        val testObserverQuery = candidateDatabase.candidateDAO()
                .fetchCandidates().test()

        testObserverQuery.assertComplete()
        testObserverQuery.assertNoErrors()

        Assert.assertTrue(testObserverQuery.values()[0].size == 1)
    }

    @Test
    fun writeCandidateWithSameEmail() {
        val candidate1 : Candidate = CandidateEntity(name = "Haroldo",
                email = "olivierisoares@gmail.com",
                phoneNumber = "+48 504 203 260", assessment = "A")

        val candidate2 : Candidate = CandidateEntity(name = "John Doe",
                email = "olivierisoares@gmail.com",
                phoneNumber = "+48 504 203 260", assessment = "A")

        val testObserverInsert1 = candidateDatabase.candidateDAO()
                .insertCompletable(candidate1.toEntity()).test()

        testObserverInsert1.assertComplete()
        testObserverInsert1.assertNoErrors()

        val testObserverInsert2 = candidateDatabase.candidateDAO()
                .insertCompletable(candidate2.toEntity()).test()

        testObserverInsert2.assertNotComplete()

    }
}