package pepe.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pepe.Pepe;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pepe pepe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pepe.png"));
    private Image pepeImage = new Image(this.getClass().getResourceAsStream("/images/pepe_sad.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pepe.getResponse(input);
        String commandType = pepe.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPepeDialog(response, pepeImage, commandType)
        );
        userInput.clear();

        // If the user types "bye", close the window after 3 seconds
        if (input.equalsIgnoreCase("bye")) {
            javafx.animation.PauseTransition delay = new javafx
                    .animation.PauseTransition(javafx.util.Duration.seconds(3));
            delay.setOnFinished(event -> {
                // Get the current stage and close it
                javafx.stage.Stage stage = (javafx.stage.Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}

