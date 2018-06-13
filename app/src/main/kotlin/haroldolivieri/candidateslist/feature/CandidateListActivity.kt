package haroldolivieri.candidateslist.feature

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import haroldolivieri.candidateslist.Candidate

import haroldolivieri.candidateslist.R
import haroldolivieri.candidateslist.view.ViewUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CandidateListActivity : DaggerAppCompatActivity(), CandidateListContract.View {

    @Inject lateinit var presenter : CandidateListContract.Presenter

    private val adapter by lazy {
        CandidateAdapter(onClick = { product ->

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate()
        setupRecyclerView()
    }

    override fun showList(candidates: List<Candidate>) {
        adapter.updateList(candidates)
    }

    override fun showError(message: String) {
        ViewUtils.showSnackBar(recyclerView, message)
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                linearLayoutManager.orientation)

        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.setEmptyView(emptyView)
    }

}
