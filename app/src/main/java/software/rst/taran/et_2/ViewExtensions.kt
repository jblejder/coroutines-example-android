package software.rst.taran.et_2

import android.view.View
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

fun View.onClick(action: suspend (View) -> Unit) {
    setOnClickListener {
        GlobalScope.launch(Dispatchers.Main) {
            action.invoke(it)
        }
    }
}
