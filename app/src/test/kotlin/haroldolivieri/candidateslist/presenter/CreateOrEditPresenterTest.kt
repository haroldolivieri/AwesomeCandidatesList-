package haroldolivieri.candidateslist.presenter

import haroldolivieri.candidateslist.feature.createoredit.CreateOrEditContract
import haroldolivieri.candidateslist.feature.createoredit.CreateOrEditPresenter
import haroldolivieri.candidateslist.helper.RxImmediateSchedulerRule
import haroldolivieri.candidateslist.helper.any
import haroldolivieri.candidateslist.repository.CandidatesRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class CreateOrEditPresenterTest {
    @Rule
    @JvmField
    val schedulers = RxImmediateSchedulerRule()

    @Mock
    private
    lateinit var repository: CandidatesRepository

    @Mock
    lateinit var view: CreateOrEditContract.View

    private lateinit var presenter: CreateOrEditPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CreateOrEditPresenter(view, repository)
    }

    @Test
    fun test_fetch_data_from_repository() {
        Mockito.`when`(repository.saveCandidate(any())).thenReturn(Completable.complete())

        presenter.save("","","","A")

        Mockito.verify(view).onSuccess()
        Mockito.verify(view, Mockito.never()).showError(any())
    }

    @Test
    fun test_fetch_data_from_repository_with_error() {

        val errorMessage = "ERROR"
        Mockito.`when`(repository.saveCandidate(any()))
                .thenReturn(Completable.error(Throwable(errorMessage)))

        presenter.save("","","","A")

        Mockito.verify(view, Mockito.never()).onSuccess()
        Mockito.verify(view).showError(errorMessage)
    }
}
