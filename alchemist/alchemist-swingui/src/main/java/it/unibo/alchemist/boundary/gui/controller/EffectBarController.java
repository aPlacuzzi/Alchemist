package it.unibo.alchemist.boundary.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;

import it.unibo.alchemist.boundary.gui.effects.Effect;
import it.unibo.alchemist.boundary.gui.effects.EffectBuilderFX;
import it.unibo.alchemist.boundary.gui.utility.FXResourceLoader;
import it.unibo.alchemist.boundary.gui.view.EffectCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import jiconfont.icons.GoogleMaterialDesignIcons;

/**
 * This class models a JavaFX controller for EffectBar.fxml.
 */
public class EffectBarController implements Initializable {
    /** Layout path. */
    public static final String EFFECT_BAR_LAYOUT = "EffectBar";

    @FXML
    private JFXButton addEffect;
    @FXML
    private ListView<Effect> effectsList;
    @FXML
    private Label groupName;
    @FXML
    private JFXButton backToGroups;

    private ObservableList<Effect> observableList;
    private EffectBuilderFX effectBuilder;

    private final JFXDrawersStack stack;
    private final JFXDrawer thisDrawer;

    /**
     * Default constructor.
     * 
     * @param stack
     *            the stack where to open the effect properties
     * @param thisDrawer
     *            the drawer the layout this controller is assigned to is loaded
     *            into
     */
    public EffectBarController(final JFXDrawersStack stack, final JFXDrawer thisDrawer) {
        this.stack = stack;
        this.thisDrawer = thisDrawer;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        assert addEffect != null : FXResourceLoader.getInjectionErrorMessage("add", EFFECT_BAR_LAYOUT);
        assert effectsList != null : FXResourceLoader.getInjectionErrorMessage("effectsList", EFFECT_BAR_LAYOUT);
        assert groupName != null : FXResourceLoader.getInjectionErrorMessage("groupName", EFFECT_BAR_LAYOUT);
        assert backToGroups != null : FXResourceLoader.getInjectionErrorMessage("backToGroups", EFFECT_BAR_LAYOUT);

        this.addEffect.setText("");
        this.addEffect.setGraphic(FXResourceLoader.getWhiteIcon(GoogleMaterialDesignIcons.ADD));

        this.effectBuilder = new EffectBuilderFX();

        this.addEffect.setOnAction(e -> {
            addEffectToList();
        });

        this.backToGroups.setOnAction(e -> {
            this.stack.toggle(thisDrawer, false);
        });

    }

    private void addEffectToList() {
        final Effect choice = effectBuilder.chooseAndLoad();
        if (choice != null) {
            // final String name = choice.getName() + " " +
            // this.getObservableList().size();
            // TODO add setName to effect
            this.getObservableList().add(choice);
            // this.getObservableList().get(this.getObservableList().size() -
            // 1).setName(name);
            // TODO add setName to effect
            this.effectsList.refresh();
        }
    }

    private ObservableList<Effect> getObservableList() {
        if (this.observableList == null) {
            this.observableList = FXCollections.observableArrayList();
            this.effectsList.setItems(observableList);
            this.effectsList.setCellFactory(lv -> new EffectCell(stack, thisDrawer));
            // TODO check
        }
        return this.observableList;
    }
}
