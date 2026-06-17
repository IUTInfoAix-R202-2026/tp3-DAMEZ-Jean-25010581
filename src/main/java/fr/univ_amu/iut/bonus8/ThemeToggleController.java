package fr.univ_amu.iut.bonus8;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

/**
 * Contrôleur du bonus 8 - bascule de thème.
 *
 * <p>Le clic sur le {@link ToggleButton} remplace la feuille CSS courante de la {@link
 * javafx.scene.Scene} : {@code theme-clair.css} si non sélectionné, {@code theme-sombre.css} sinon.
 */
public class ThemeToggleController {

  static final String CSS_CLAIR = "theme-clair.css";
  static final String CSS_SOMBRE = "theme-sombre.css";

  @FXML private ToggleButton boutonTheme;

  @FXML private Region racine;

  @FXML
  private void initialize() {
    String cssClair = getClass().getResource(CSS_CLAIR).toExternalForm();
    String cssSombre = getClass().getResource(CSS_SOMBRE).toExternalForm();

    var appliquerTheme =
        (Runnable)
            () -> {
              if (racine.getScene() == null) {
                return;
              }
              String url = boutonTheme.isSelected() ? cssSombre : cssClair;
              racine.getScene().getStylesheets().setAll(url);
              boutonTheme.setText(boutonTheme.isSelected() ? "☀️ Mode clair" : "🌙 Mode sombre");
            };

    boutonTheme
        .selectedProperty()
        .addListener((observable, oldValue, newValue) -> appliquerTheme.run());
    racine.sceneProperty().addListener((observable, oldScene, newScene) -> appliquerTheme.run());
    appliquerTheme.run();
  }
}
