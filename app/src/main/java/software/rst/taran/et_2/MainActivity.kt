package software.rst.taran.et_2

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import software.rst.taran.Gate
import software.rst.taran.R
import software.rst.taran.TaranService

class MainActivity : ScopedActivity() {

    val taran = TaranService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpClicks()
    }

    private fun setUpClicks() {
        button0.onClick { triggerGate(it, Gate.Gate0) }
        button1.onClick { triggerGate(it, Gate.Gate0) }
    }

    private suspend fun triggerGate(view: View, gateId: String) {
        view.isEnabled = false
        async(Dispatchers.IO) { taran.openGate(gateId) }.await()
        view.isEnabled = true
    }
}
