package software.rst.taran.et_3

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main_2.*
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import software.rst.taran.Gate
import software.rst.taran.R
import software.rst.taran.TaranServiceActor
import software.rst.taran.et_2.ScopedActivity
import software.rst.taran.et_2.onClick
import java.util.concurrent.TimeUnit

class MainActivity : ScopedActivity() {

    val taran = TaranServiceActor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        setUpClicks()
    }

    private fun setUpClicks() {
        button0.onClick { triggerGate(it, Gate.Gate0) }
        button1.onClick { triggerGate(it, Gate.Gate0) }
    }

    private suspend fun triggerGate(view: View, gateId: String) {
        view.isEnabled = false
        blinkAlarm(5, 2)
        async(Dispatchers.IO) { taran.inbox.offer(TaranServiceActor.Command.OpenGate(gateId)) }.await()
        view.isEnabled = true
    }

    private suspend fun blinkAlarm(times: Int, interval: Long) {
        val millis = TimeUnit.SECONDS.toMillis(interval)
        launch {
            for (i in 0 until times) {
                setAlarmOnOf(true)
                delay(millis)
                setAlarmOnOf(false)
                delay(millis)
            }
        }
    }

    fun setAlarmOnOf(on: Boolean) {
        val color = when (on) {
            true -> R.color.alarm_on
            false -> R.color.alarm_off
        }.let { ContextCompat.getColor(this, it) }
        alarm.setBackgroundColor(color)
    }
}
