package haroldolivieri.candidateslist.repository

import haroldolivieri.candidateslist.helper.RxImmediateSchedulerRule
import haroldolivieri.candidateslist.helper.candidates
import haroldolivieri.candidateslist.repository.local.CandidateDAO
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`


class RepositoryTest {

    @Rule
    @JvmField val schedulers = RxImmediateSchedulerRule()

    @Mock
    private lateinit var candidateDAO: CandidateDAO

    @InjectMocks
    private lateinit var repository: CandidatesRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun readDataFromDAO() {
        `when`(candidateDAO.fetchCandidates()).thenReturn(Single.just(candidates))
        val testObserver = repository.fetchCandidates().test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        Assert.assertTrue(testObserver.values()[0].size == candidates.size)
    }

    @Test
    fun updateDataFromDAO() {
        val candidate = candidates[0]

        `when`(candidateDAO.upsertCompletable(candidate)).thenReturn(Completable.complete())
        val testObserver = repository.saveCandidate(candidate).test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }
}