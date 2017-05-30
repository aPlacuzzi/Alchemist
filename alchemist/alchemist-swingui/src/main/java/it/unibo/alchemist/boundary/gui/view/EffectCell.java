package it.unibo.alchemist.boundary.gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXToggleButton;

import it.unibo.alchemist.boundary.gui.effects.Effect;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.DataFormat;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * This ListView cell implements the {@link AbstractEffectCell} for containing
 * an {@link Effect}. It has a name that identifies the Effect and when clicked
 * should open another view to edit effect-specific parameters.
 */
public class EffectCell extends AbstractEffectCell<Effect> {
    private static final String DEFAULT_NAME = "Unnamed effect";
    private final JFXDrawersStack stack;
    private final JFXDrawer thisDrawer;

    /**
     * Default constructor.
     * 
     * @param effectName
     *            the name of the effect
     */
    public EffectCell(final String effectName, final JFXDrawersStack stack, final JFXDrawer thisDrawer) {
        super(new Label(effectName), new JFXToggleButton());

        this.stack = stack;
        this.thisDrawer = thisDrawer;

        this.getLabel().setTextAlignment(TextAlignment.CENTER);
        this.getLabel().setFont(Font.font(this.getLabel().getFont().getFamily(), FontWeight.BOLD, this.getLabel().getFont().getSize()));

        // this.getLabel().textProperty().addListener((observable, oldValue,
        // newValue) -> this.getItem().setName(newValue));
        // TODO add setName to Effect
        // this.getToggle().selectedProperty().addListener((observable,
        // oldValue, newValue) -> this.getItem().setVisibility(newValue));
        // TODO add setVisibility to Effect

        this.getLabel().setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                final Object source = click.getSource();
                final Label label;

                if (source instanceof Label) {
                    label = (Label) source;
                } else {
                    throw new IllegalStateException("EventHandler for label rename not associated to a label");
                }

                final TextInputDialog dialog = new TextInputDialog(label.getText());
                dialog.setTitle("Rename the Effect");
                dialog.setHeaderText("Please enter new name:");
                dialog.setContentText(null);

                dialog.showAndWait().ifPresent(name -> label.setText(name));
            }
        });

        final JFXDrawer propertiesDrawer = new JFXDrawer();

        this.getPane().setOnMouseClicked(event -> {
            // To not interfere with label double-click action
            if (event.getClickCount() != 2) {
                // Drawer size is modified every time it's opened
                if (propertiesDrawer.isHidden() || propertiesDrawer.isHidding()) {
                    propertiesDrawer.setDefaultDrawerSize(stack.getWidth());
                    this.stack.toggle(propertiesDrawer, true);
                } else if (propertiesDrawer.isShown() || propertiesDrawer.isShowing()) {
                    this.stack.getChildren().forEach(drawer -> this.stack.toggle((JFXDrawer) drawer, false));
                } else {
                    throw new IllegalStateException("Drawer disappeared");
                }
            }
        });
    }

    /**
     * Constructor. Creates a cell with a default name.
     */
    public EffectCell(final JFXDrawersStack stack, final JFXDrawer thisDrawer) {
        this(DEFAULT_NAME, stack, thisDrawer);
    }

    /**
     * Returns the label with the effect name.
     * 
     * @return the label
     */
    protected Label getLabel() {
        return (Label) super.getInjectedNodeAt(0);
    }

    /**
     * Returns the toggle of the visibility.
     * 
     * @return the toggle
     */
    protected JFXToggleButton getToggle() {
        return (JFXToggleButton) super.getInjectedNodeAt(1);
    }

    @Override
    public DataFormat getDataFormat() {
        final Effect item = this.getItem();

        if (item == null) {
            return Effect.DATA_FORMAT;
        } else {
            return item.getDataFormat();
        }
    }

    private JFXDrawer buildDrawer(final Effect effect) {
        // TODO Auto-generated method stub
        return null;
    }

}
