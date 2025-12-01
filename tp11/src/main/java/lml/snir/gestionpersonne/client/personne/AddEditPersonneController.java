package lml.snir.gestionpersonne.client.personne;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lml.snir.gestionpersonne.client.JavaFXGUI;
import lml.snir.gestionpersonne.client.PersonneModel;
import lml.snir.gestionpersonne.metier.entity.Administrateur;
import lml.snir.gestionpersonne.metier.entity.Agent;
import lml.snir.gestionpersonne.metier.entity.Personne;

/**
 *
 * @author fanou
 */
public class AddEditPersonneController implements Initializable {

    private Personne personne;
    private Stage dialogStage;

    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldAge;
    private int age;

    @FXML
    private CheckBox isMasculin;
    @FXML
    private ComboBox comboBoxType;

    private long id = -1;

    public AddEditPersonneController() {
        System.out.println("AddEditPersonneController.constructor()");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("AddEditPersonneController.initialize");
        String[] types = {Administrateur.class.getSimpleName(), Agent.class.getSimpleName()};
        this.comboBoxType.setItems(FXCollections.observableArrayList(types));
    }

    //onButtonUpdateClic
    @FXML
    public void buttonOkClick(Event evt) throws Exception {
        System.out.println("onButtonUpdateClic");
        if (isInputValid()) {
            if (this.comboBoxType.getSelectionModel().getSelectedItem().toString().equals(Administrateur.class.getSimpleName())) {
                this.personne = new Administrateur();
            } else {
                this.personne = new Agent();
            }

            this.personne.setAge(this.age);
            this.personne.setNom(this.textFieldNom.getText());
            this.personne.setPrenom(this.textFieldPrenom.getText());
            this.personne.setMasculin(this.isMasculin.isSelected());

            if (this.id != -1) {
                this.personne.setId(this.id);
            }

            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (this.textFieldNom.getText() == null || this.textFieldNom.getText().length() == 0) {
            errorMessage += "Nom invallide!\n";
        }
        if (this.textFieldPrenom.getText() == null || this.textFieldPrenom.getText().length() == 0) {
            errorMessage += "Prenom invalide!\n";
        }
        if (this.textFieldAge.getText() == null || this.textFieldAge.getText().length() == 0) {
            errorMessage += "Age invalide!\n";
        } else {
            try {
                this.age = Integer.parseInt(this.textFieldAge.getText());
            } catch (NumberFormatException nfe) {
                errorMessage += "Age invalide!\n";
            }
        }
        if (this.comboBoxType.getSelectionModel().isEmpty()) {
            errorMessage += "Vous deves selectionner un type !\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    //onButtonUpdateClic
    @FXML
    public void buttonCancelClick(Event evt) throws Exception {
        System.out.println("onButtonUpdateClic");
        this.dialogStage.close();
    }

    /**
     * @return the personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personneModel
     */
    public void setPersonne(PersonneModel personneModel) {
        this.textFieldAge.setText(String.valueOf(personneModel.getAge()));
        this.textFieldNom.setText(personneModel.getNom());
        this.textFieldPrenom.setText(personneModel.getPrenom());
        this.comboBoxType.getSelectionModel().select(personneModel.getType());
        this.isMasculin.setSelected(personneModel.isMasculin());
        this.id = personneModel.getId();
    }

    public void show(Scene scene, String title) {
        // Create the dialog Stage.
        this.dialogStage = new Stage();

        this.dialogStage.initModality(Modality.WINDOW_MODAL);
        this.dialogStage.initOwner(JavaFXGUI.stage);
        this.dialogStage.setScene(scene);

        this.dialogStage.setTitle(title);
        this.dialogStage.showAndWait();
    }
}
