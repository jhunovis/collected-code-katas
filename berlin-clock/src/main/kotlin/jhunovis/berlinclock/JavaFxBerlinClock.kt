package jhunovis.berlinclock

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.css.PseudoClass
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import javafx.util.Duration
import java.net.URL
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Animates a [BerlinClock] with JavaFX.
 *
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

/**
 * Binds the JavaFX layout definition to the Berlin Clock and periodically updates the view.
 */
class BerlinClockController {

    /** For accessing all the shapes representing the lights of the Berlin Clock.*/
    @FXML private var lights: Parent? = null
    /** Shows the real time in the view. */
    @FXML private var label: Label? = null

    /**
     * Animation is done by computing the difference between the currently shown [BerlinClock] and a Berlin Clock
     * for the moment the view is updating. Thus only those controls get updated which state has actually changed.
     */
    private var currentBerlinClock : BerlinClock = berlinClockWithAllLightsOff()

    @FXML private fun initialize() {
        val timeline = Timeline(KeyFrame(Duration(500.0), EventHandler {
            updateView()
        }))
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.playFromStart()
    }

    fun updateView() {
        val now = LocalTime.now()
        val nowAsBerlinClock = BerlinClock(now)
        currentBerlinClock.javaFxIdsOfLightsSwitchedOn(nowAsBerlinClock).switchAllOn(true)
        currentBerlinClock.javaFxIdsOfLightsSwitchedOff(nowAsBerlinClock).switchAllOn(false)
        label?.text = now.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
        currentBerlinClock = nowAsBerlinClock
    }

    /**
     * Toggles lights on or off via a CSS-pseudo class.
     */
    private fun List<String>.switchAllOn(on : Boolean) =
        this.forEach {
            lights?.lookup("#" + it)?.pseudoClassStateChanged(PseudoClass.getPseudoClass("on"), on)
        }

}

fun main(args : Array<String>) {
    Application.launch(JavaFxBerlinClock::class.java)
}

