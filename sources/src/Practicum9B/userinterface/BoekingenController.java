package src.Practicum9B.userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;
import src.Practicum9B.model.Hotel;
import src.Practicum9B.model.KamerType;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BoekingenController implements Initializable {

	@FXML
	private Label labelHeader;
	@FXML
	private TextField fieldName;
	@FXML
	private TextField fieldAddress;
	@FXML
	private DatePicker pickerArrival;
	@FXML
	private DatePicker pickerLeave;
	@FXML
	private ComboBox<KamerType> comboType;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ObservableList<KamerType> kamerTypen = FXCollections.observableArrayList();
		kamerTypen.addAll(Hotel.getHotel().getKamerTypen());
		this.comboType.setItems(kamerTypen);
	}

	@FXML
	private void onActionReset() {
		this.labelHeader.setText("Voer uw gegevens in!");
		this.fieldName.setText(null);
		this.fieldAddress.setText(null);
		this.pickerArrival.setValue(null);
		this.pickerLeave.setValue(null);
		this.comboType.setValue(null);
	}

	@FXML
	private void onActionBoek(ActionEvent event) {
		String naam = this.fieldName.getText().trim();
		if (naam.isEmpty()) {
			this.labelHeader.setText("Voer een geldige naam in!");
			return;
		}
		String adres = this.fieldAddress.getText().trim();
		if (adres.isEmpty()) {
			this.labelHeader.setText("Voer een geldig adres in!");
			return;
		}
		LocalDate datumStart = this.pickerArrival.getValue();
		if (datumStart == null || datumStart.isBefore(LocalDate.now())) {
			this.labelHeader.setText("Voer een geldige begindatum in!");
			return;
		}
		LocalDate datumEind = this.pickerLeave.getValue();
		if (datumEind == null || datumEind.isBefore(LocalDate.now())) {
			this.labelHeader.setText("Voer een geldige einddatum in!");
			return;
		}
		if (datumStart.isAfter(datumEind)) {
			this.labelHeader.setText("Begindatum moet eerder dan einddatum zijn!");
			return;
		}
		KamerType type = this.comboType.getValue();
		if (type == null) {
			this.labelHeader.setText("Selecteer een kamertype!");
			return;
		}
		try {
			Hotel.getHotel().voegBoekingToe(datumStart, datumEind, naam, adres, type);
			Window window = ((Button) event.getSource()).getScene().getWindow();
			((Stage) window).close();
		} catch (Exception e) {
			this.labelHeader.setText("Onbekende fout: " + e.toString());
		}
	}
}
