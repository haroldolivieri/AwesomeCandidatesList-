package haroldolivieri.candidateslist.feature.createoredit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import haroldolivieri.candidateslist.domain.Candidate
import haroldolivieri.candidateslist.R
import haroldolivieri.candidateslist.feature.createoredit.CreateOrEditActivity.Companion.CANDIDATE
import haroldolivieri.candidateslist.feature.list.CandidateListActivity
import haroldolivieri.candidateslist.view.ViewUtils
import kotlinx.android.synthetic.main.activity_create_or_edit_candidate.*
import javax.inject.Inject

fun Context.createIntent(): Intent =
        Intent(this, CreateOrEditActivity::class.java)

fun Context.editIntent(candidate: Candidate): Intent =
        this.createIntent().apply { putExtra(CANDIDATE, candidate) }

class CreateOrEditActivity : DaggerAppCompatActivity(), CreateOrEditContract.View {

    companion object {
        const val CANDIDATE = "candidate"
        private val TAG = CandidateListActivity::class.java.simpleName
    }

    private val candidate by lazy {
        intent.getSerializableExtra(CANDIDATE) as Candidate?
    }

    @Inject
    lateinit var presenter: CreateOrEditContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_or_edit_candidate)

        checkIfEditableMode()

        btnSave.setOnClickListener {
            val array = resources.getStringArray(R.array.grades)

            presenter.save(email = edtEmail.text.toString(),
                    phone = edtPhone.text.toString(),
                    name = edtName.text.toString(),
                    grade = array[spnGrades.selectedItemPosition])
        }
    }

    private fun checkIfEditableMode() {
        candidate.let { }
    }

    override fun showError(message: String) {
        Log.d(TAG, message)
        ViewUtils.showSnackBar(edtEmail, R.string.unexpectedError)
    }

    override fun onSuccess() {
        finish()
    }
}


