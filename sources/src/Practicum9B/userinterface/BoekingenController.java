package src.Practicum9B.userinterface;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import src.Practicum9B.model.KamerType;

public class BoekingenController {

	@FXML
	private Label labelHeader;
	@FXML
	private Button buttonReset;
	@FXML
	private Button buttonBoek;
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

	@FXML
	private void onActionReset(ActionEvent event) {
	}

	@FXML
	private void onActionBoek(ActionEvent event) {
	}
}
