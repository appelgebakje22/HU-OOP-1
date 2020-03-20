package src.Practicum11;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	private Label labelInput;
	@FXML
	private Label labelOutput;
	@FXML
	private TextField fieldExchange;
	@FXML
	private Label labelStatus;
	private File fileIn, fileOut;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.labelInput.setText(null);
		this.labelOutput.setText(null);
		this.labelStatus.setText(null);
	}

	public void handleButtonInput() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Selecteer invoerbestand");
		chooser.setInitialDirectory(new File("."));
		this.fileIn = chooser.showOpenDialog(this.labelInput.getScene().getWindow());
		this.labelInput.setText(this.fileIn == null ? null : this.fileIn.getPath());
	}

	public void handleButtonOutput() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Selecteer uitvoerbestand");
		chooser.setInitialDirectory(new File("."));
		this.fileOut = chooser.showSaveDialog(this.labelOutput.getScene().getWindow());
		this.labelOutput.setText(this.fileOut == null ? null : this.fileOut.getPath());
	}

	public void handleButtonProcess() {
		if (this.fileIn == null || !this.fileIn.exists() || !this.fileIn.canRead()) {
			this.labelStatus.setText("Selecteer een geldig invoerbestand!");
			return;
		}
		if (this.fileOut == null) {
			this.labelStatus.setText("Selecteer een geldig uitvoerbestand!");
			return;
		}
		double exchange;
		try {
			exchange = Double.parseDouble(this.fieldExchange.getText());
		} catch (NumberFormatException ignored) {
			this.labelStatus.setText("Voer een geldig getal in!");
			return;
		}
		try (
				BufferedReader reader = new BufferedReader(new FileReader(this.fileIn));
				PrintWriter writer = new PrintWriter(new FileWriter(this.fileOut))
		) {
			String line;
			while ((line = reader.readLine()) != null)
				writer.println(Utils.transformLine(line, exchange));
		} catch (IOException e) {
			this.labelStatus.setText("Error: " + e.toString());
		}
		this.labelStatus.setText("klaar!");
		try {
			Desktop.getDesktop().open(this.fileOut);
		} catch (IOException ignored) {
		}
	}
}