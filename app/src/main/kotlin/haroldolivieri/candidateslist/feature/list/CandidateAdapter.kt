package haroldolivieri.candidateslist.feature.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import haroldolivieri.candidateslist.domain.Candidate
import haroldolivieri.candidateslist.R
import haroldolivieri.candidateslist.view.ItemTouchHelperAdapter

class CandidateAdapter(private var adapterList: MutableList<Candidate>? = null,
                       val onClick : (Candidate) -> Unit,
                       val onDeleted: (Candidate) -> Unit) :
        RecyclerView.Adapter<CandidateViewHolder>(), ItemTouchHelperAdapter {

    fun updateList(list : List<Candidate>) {
        this.adapterList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_candidate, parent, false)
        return CandidateViewHolder(view, onClick)
    }

    override fun getItemCount(): Int = adapterList?.size ?: 0

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        adapterList?.get(position)?.let { holder.bind(it) }
    }

    override fun onItemDismiss(position: Int) {
        adapterList?.get(position)?.let { onDeleted.invoke(it) }
        adapterList?.removeAt(position)
        if (adapterList?.size == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRemoved(position)
        }
    }
}

class CandidateViewHolder(itemView: View,
                          val onClick: (Candidate) -> Unit)
    : RecyclerView.ViewHolder(itemView) {

    private val candidateName by lazy { itemView.findViewById<TextView>(R.id.name) }
    private val candidateEmail by lazy { itemView.findViewById<TextView>(R.id.email) }
    private val candidatePhone by lazy { itemView.findViewById<TextView>(R.id.phone) }
    private val candidateGrade by lazy { itemView.findViewById<TextView>(R.id.grade) }

    fun bind(candidate: Candidate) {
        candidate.apply {
            candidateName.text = if (!name.isEmpty()) name else "Not informed"
            candidateEmail.text = if (!email.isEmpty()) email else "Not informed"
            candidatePhone.text = if (!phoneNumber.isEmpty()) phoneNumber else "Not informed"
            candidateGrade.text = assessmentGrade.name
            itemView.setOnClickListener { onClick.invoke(this) }
        }
    }
}
