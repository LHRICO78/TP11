package lml.snir.gestionpersonne.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lml.snir.javafx.Frame;
import lml.snir.gestionpersonne.client.personne.AddEditPersonneController;
import lml.snir.gestionpersonne.metier.entity.Administrateur;
import lml.snir.gestionpersonne.metier.entity.Agent;
import lml.snir.gestionpersonne.metier.MetierFactory;
import lml.snir.gestionpersonne.metier.entity.Attribution;
import lml.snir.gestionpersonne.metier.entity.Badge;
import lml.snir.gestionpersonne.metier.entity.Personne;
import lml.snir.gestionpersonne.metier.transactionel.AttributionService;
import lml.snir.gestionpersonne.metier.transactionel.BadgeService;
import lml.snir.gestionpersonne.metier.transactionel.PersonneService;

/**
 *
 * @author fanou
 */
public class JavaFXGUI extends Application implements Initializable {

    private PersonneService pSrv;
    private BadgeService bSrv;
    private AttributionService attrSrv;
    private Object selectedItem;
    public static Stage stage;

    @FXML
    TableView tableView;

    @FXML
    ChoiceBox choiceBoxType;

    public void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene scene = Frame.createScene("/fxml/main.fxml", 1000, 450, null, getClass());
        Frame.setMainStage(primaryStage);
        primaryStage.setTitle("TP7 GUI JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.pSrv = MetierFactory.getPersonneService();
            this.bSrv = MetierFactory.getBadgeService();
            this.attrSrv = MetierFactory.getAttributionService();
            this.fillTableViewPersonne(this.pSrv.getAll());
            this.choiceBoxType.getItems().add(("ALL"));
            this.choiceBoxType.getItems().add(("ADMINISTRATOR"));
            this.choiceBoxType.getItems().add(("AGENT"));
            this.choiceBoxType.getItems().add(("HOMME"));
            this.choiceBoxType.getItems().add(("FEMME"));
        } catch (Exception ex) {
            Logger.getLogger(JavaFXGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillTableViewPersonne(List<Personne> personnes) {
        this.tableView.getColumns().clear();

        TableColumn<PersonneModel, String> column1
                = new TableColumn<>("First Name");
        column1.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));
        this.tableView.getColumns().add(column1);

        TableColumn<PersonneModel, String> column2
                = new TableColumn<>("Name");
        column2.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
        this.tableView.getColumns().add(column2);

        TableColumn<PersonneModel, Integer> column3
                = new TableColumn<>("Age");
        column3.setCellValueFactory(
                new PropertyValueFactory<>("age"));
        this.tableView.getColumns().add(column3);

        TableColumn<PersonneModel, Boolean> column4
                = new TableColumn<>("Masculin");
        column4.setCellValueFactory(
                new PropertyValueFactory<>("masculin"));
        this.tableView.getColumns().add(column4);

        TableColumn<PersonneModel, String> column5
                = new TableColumn<>("Type");
        column5.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        this.tableView.getColumns().add(column5);

        List<PersonneModel> personneModels = new ArrayList<>();
        for (Personne p : personnes) {
            PersonneModel pm = new PersonneModel();
            pm.setId(p.getId());
            pm.setAge(p.getAge());
            pm.setMasculin(p.isMasculin());
            pm.setNom(p.getNom());
            pm.setPrenom(p.getPrenom());
            pm.setType(p.getClass().getSimpleName());
            personneModels.add(pm);
        }
        this.tableView.setItems(FXCollections.observableArrayList(personneModels));

    }

    private void fillTableViewBadge(List<Badge> badges) {
        this.tableView.getColumns().clear();

        TableColumn<Badge, String> column1
                = new TableColumn<>("Contenu");
        column1.setCellValueFactory(
                new PropertyValueFactory<>("contenu"));
        this.tableView.getColumns().add(column1);

        this.tableView.setItems(FXCollections.observableArrayList(badges));

    }

    private void fillTableViewAttribution(List<Attribution> attributions) {
        this.tableView.getColumns().clear();

        TableColumn<PersonneModel, String> column1
                = new TableColumn<>("Personne");
        column1.setCellValueFactory(
                new PropertyValueFactory<>("personne"));
        this.tableView.getColumns().add(column1);

        TableColumn<PersonneModel, String> column2
                = new TableColumn<>("Badge");
        column2.setCellValueFactory(
                new PropertyValueFactory<>("badge"));
        this.tableView.getColumns().add(column2);
        this.tableView.setItems(FXCollections.observableArrayList(attributions));

    }

    //onButtonAddClic
    @FXML
    public void onButtonAddClic(Event evt) throws Exception {
        System.out.println("onButtonAddClic");
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(AddEditPersonneController.class.getResourceAsStream("/fxml/AddEditPersonneView.fxml"));
        Scene scene = new Scene(rootNode, 900, 500);
        AddEditPersonneController dlg = loader.getController();
        dlg.show(scene, "Add Personne");

        Personne p = dlg.getPersonne();
        if (p != null) {
            System.out.println(p);
            this.pSrv.add(p);
            this.fillTableViewPersonne(this.pSrv.getAll());
        }
    }

    //onButtonUpdateClic
    @FXML
    public void onButtonUpdateClic(Event evt) throws Exception {
        System.out.println("onButtonUpdateClic");
        if (this.selectedItem != null) {
            if (this.selectedItem instanceof PersonneModel) {
                FXMLLoader loader = new FXMLLoader();
                Parent rootNode = (Parent) loader.load(AddEditPersonneController.class.getResourceAsStream("/fxml/AddEditPersonneView.fxml"));
                Scene scene = new Scene(rootNode, 900, 500);
                AddEditPersonneController dlg = loader.getController();
                dlg.setPersonne((PersonneModel) this.selectedItem);
                dlg.show(scene, "Update Personne");

                Personne p = dlg.getPersonne();
                if (p != null) {
                    System.out.println(p);
                    this.pSrv.update(p);
                    this.fillTableViewPersonne(this.pSrv.getAll());
                }
            }
        }
    }

    //onButtonRemoveClic
    @FXML
    public void onButtonRemoveClic(Event evt) throws Exception {
        System.out.println("onButtonRemoveClic");
        if (this.selectedItem != null) {
            if (this.selectedItem instanceof PersonneModel) {
                PersonneModel pm = (PersonneModel) this.selectedItem;
                Personne p = this.pSrv.getById(pm.getId());
                if (p != null) {
                    System.out.println(p);
                    this.pSrv.remove(p);
                    this.fillTableViewPersonne(this.pSrv.getAll());
                }
            }
        }
    }

    @FXML
    public void onMousePressedTableView(Event evt) throws Exception {
        System.out.println("onMousePressedTableView");
        this.selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        System.out.println(this.selectedItem);
    }

    @FXML
    public void onChoiceBoxChange(Event evt) throws Exception {
        int selectedIndex = this.choiceBoxType.getSelectionModel().getSelectedIndex();
        System.out.println("onChoiceBoxChange " + selectedIndex);
        switch (selectedIndex) {
            case 1:
                this.fillTableViewPersonne(this.pSrv.getAllByType(Administrateur.class));
                break;
            case 2:
                this.fillTableViewPersonne(this.pSrv.getAllByType(Agent.class));
                break;
            case 3:
                this.fillTableViewPersonne(this.pSrv.getByGenre(true));
                break;
            case 4:
                this.fillTableViewPersonne(this.pSrv.getByGenre(false));
                break;
            default:
                this.fillTableViewPersonne(this.pSrv.getAllSorted());

        }
    }

    @FXML
    public void onButtonBadgeClic(Event evt) throws Exception {
        this.fillTableViewBadge(this.bSrv.getAll());
    }

    @FXML
    public void onButtonPersonneClic(Event evt) throws Exception {
        this.fillTableViewPersonne(this.pSrv.getAllSorted());
    }

    @FXML
    public void onButtonAttributionClic(Event evt) throws Exception {
        this.fillTableViewAttribution(this.attrSrv.getAll());
    }

}
