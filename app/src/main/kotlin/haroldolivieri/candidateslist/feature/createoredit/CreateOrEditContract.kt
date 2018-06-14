package haroldolivieri.candidateslist.feature.createoredit

object CreateOrEditContract {

    interface View {
        fun showError(message: String)
        fun onSuccess()
    }

    interface Presenter {
        fun save(email:String, name: String, phone: String, grade: String)
    }
}