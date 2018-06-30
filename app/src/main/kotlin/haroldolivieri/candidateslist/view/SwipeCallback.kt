package haroldolivieri.candidateslist.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper.Callback
import android.support.v7.widget.helper.ItemTouchHelper.LEFT

class SwipeCallback(private val adapterHelperAdapter: ItemTouchHelperAdapter) : Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView?,
                                  viewHolder: RecyclerView.ViewHolder?): Int =
            makeMovementFlags(0, LEFT)


    override fun onMove(recyclerView: RecyclerView?,
                        viewHolder: RecyclerView.ViewHolder?,
                        target: RecyclerView.ViewHolder?): Boolean = false


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        viewHolder?.adapterPosition?.let { adapterHelperAdapter.onItemDismiss(it) }
    }
}