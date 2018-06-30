package haroldolivieri.candidateslist.feature.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import haroldolivieri.candidateslist.domain.Candidate

import haroldolivieri.candidateslist.R
import haroldolivieri.candidateslist.feature.createoredit.createIntent
import haroldolivieri.candidateslist.feature.createoredit.editIntent
import haroldolivieri.candidateslist.view.ViewUtils
import kotlinx.android.synthetic.main.activity_create_or_edit_candidate.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.support.v7.widget.helper.ItemTouchHelper
import haroldolivieri.candidateslist.view.SwipeCallback


class CandidateListActivity : DaggerAppCompatActivity(), CandidateListContract.View {

    companion object {
        private val TAG = CandidateListActivity::class.java.simpleName
    }

    @Inject
    lateinit var presenter: CandidateListContract.Presenter

    private val adapter by lazy {
        CandidateAdapter(onClick = {
            startActivity(editIntent(it))
        }, onDeleted = {
            presenter.deleteCandidate(it)
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        fab.setOnClickListener {
            startActivity(createIntent())
        }
    }

    override fun showList(candidates: List<Candidate>) {
        adapter.updateList(candidates)
    }

    override fun showError(message: String) {
        Log.d(TAG, message)
        ViewUtils.showSnackBar(edtEmail, R.string.unexpectedError)
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.setEmptyView(emptyView)
        ItemTouchHelper(SwipeCallback(adapter)).attachToRecyclerView(recyclerView)
    }
}