package haroldolivieri.candidateslist.view

import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import haroldolivieri.candidateslist.R

class ViewUtils {
    companion object {
        fun showSnackBar(view: View, message: Int, duration: Int = Snackbar.LENGTH_LONG) {
            showSnackBar(view, view.context.getString(message), duration)
        }

        fun showSnackBar(view: View,
                         message: String,
                         duration: Int = Snackbar.LENGTH_LONG) {

            val snackBar = Snackbar.make(view, message, duration)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.colorGrayTransparent))

            val snackText = snackBar.view
                    .findViewById<TextView>(android.support.design.R.id.snackbar_text)

            snackText.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
            snackBar.show()
        }
    }
}