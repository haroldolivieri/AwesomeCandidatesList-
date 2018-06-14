package haroldolivieri.candidateslist.presenter

import haroldolivieri.candidateslist.helper.RxImmediateSchedulerRule
import haroldolivieri.candidateslist.helper.any
import haroldolivieri.candidateslist.helper.candidates
import haroldolivieri.candidateslist.feature.list.CandidateListContract
import haroldolivieri.candidateslist.feature.list.CandidateListPresenter
import haroldolivieri.candidateslist.repository.CandidatesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ProductListPresenterTest {
    @Rule
    @JvmField
    val schedulers = RxImmediateSchedulerRule()

    @Mock
    private
    lateinit var repository: CandidatesRepository

    @Mock
    lateinit var view: CandidateListContract.View

    private lateinit var presenter: CandidateListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CandidateListPresenter(view, repository)
    }

    @Test
    fun test_fetch_data_from_repository() {
        Mockito.`when`(repository.fetchCandidates()).thenReturn(Single.just(candidates))

        presenter.fetchData()

        Mockito.verify(view).showList(candidates)
        Mockito.verify(view, Mockito.never()).showError(any())
    }

    @Test
    fun test_fetch_data_from_repository_with_error() {

        val errorMessage = "ERROR"
        Mockito.`when`(repository.fetchCandidates())
                .thenReturn(Single.error(Throwable(errorMessage)))

        presenter.fetchData()

        Mockito.verify(view, Mockito.never()).showList(candidates)
        Mockito.verify(view).showError(errorMessage)
    }
}
