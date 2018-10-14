package software.rst.taran

import software.rst.taran.Gate.Companion.Gate0
import software.rst.taran.Gate.Companion.Gate1

class TaranService {

    fun openGate(gateId: String) {
        when (gateId) {
            Gate0 -> Thread.sleep(5000)
            Gate1 -> Thread.sleep(10000)
        }
    }

}

class Gate {

    companion object {
        const val Gate0 = "gate0"
        const val Gate1 = "gate1"
    }
}