package software.rst.taran.et_1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import software.rst.taran.Gate
import software.rst.taran.R
import software.rst.taran.TaranService

class MainActivity : AppCompatActivity() {

    val taran = TaranService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpClicks()
    }

    private fun setUpClicks() {
        button0.setOnClickListener { triggerGate(it, Gate.Gate0) }
        button1.setOnClickListener { triggerGate(it, Gate.Gate0) }
    }

    private fun triggerGate(view: View, gateId: String) {
        view.isEnabled = false
        taran.openGate(gateId)
        view.isEnabled = true
    }
}