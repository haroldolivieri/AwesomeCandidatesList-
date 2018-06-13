package haroldolivieri.candidateslist.feature

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import haroldolivieri.candidateslist.Candidate
import haroldolivieri.candidateslist.R

class CandidateAdapter(var adapterList: List<Candidate>? = null,
                       val onClick : (Candidate) -> Unit) : RecyclerView.Adapter<CandidateViewHolder>() {

    fun updateList(list : List<Candidate>) {
        this.adapterList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_candidate, parent, false)
        return CandidateViewHolder(view)
    }

    override fun getItemCount(): Int = adapterList?.size ?: 0


    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        adapterList?.get(position)?.let { holder.bind(it) }
    }

}

class CandidateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val candidateName by lazy { itemView.findViewById<TextView>(R.id.name) }
    private val candidateEmail by lazy { itemView.findViewById<TextView>(R.id.email) }
    private val candidatePhone by lazy { itemView.findViewById<TextView>(R.id.phone) }
    private val candidateGrade by lazy { itemView.findViewById<TextView>(R.id.grade) }

    fun bind(candidate: Candidate) {
        candidate.apply {
            candidateName.text = name
            candidateEmail.text = email
            candidatePhone.text = phoneNumber
            candidateGrade.text = assessmentGrade.name
        }
    }
}
