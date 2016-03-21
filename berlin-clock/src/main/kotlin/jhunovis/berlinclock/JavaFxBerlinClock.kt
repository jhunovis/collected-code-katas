package jhunovis.berlinclock

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.util.Duration
import java.net.URL
import java.time.LocalTime

/**
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
class JavaFxBerlinClock : Application() {

    override fun start(stage: Stage?) {
        if (stage != null) {
            val scene = stage.loadScene()
            scene.show()
        }
    }

    private fun Stage.loadScene(): Stage {
        val root: Parent = load(fxml())
        this.scene = Scene(root, 640.0, 480.0)
        return this
    }

    private fun <T> load(resourceUrl: URL) : T = FXMLLoader.load(resourceUrl)

    private fun fxml() = urlOfResource("/fxml/BerlinClock.fxml")

    private fun urlOfResource(resource: String) = BerlinClock::class.java.getResource(resource)

}

class BerlinClockController {

    @FXML var lights: Parent? = null

    @FXML private fun initialize() {
        val timeline = Timeline(KeyFrame(Duration(500.0), EventHandler {
            update()
        }))
        timeline.autoReverseProperty().set(true)
        timeline.playFromStart()
    }

    fun update() {
        val berlinClock = BerlinClock(LocalTime.now())
    }

}

fun main(args : Array<String>) {
    Application.launch(JavaFxBerlinClock::class.java)
}

