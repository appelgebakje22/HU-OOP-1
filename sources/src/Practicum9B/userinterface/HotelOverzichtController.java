package src.Practicum9B.userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import src.Practicum9B.model.Boeking;
import src.Practicum9B.model.Hotel;

import java.io.IOException;
import java.time.LocalDate;

public class HotelOverzichtController {
	@FXML
	private Label hotelnaamLabel;
	@FXML
	private ListView<String> boekingenListView;
	@FXML
	private DatePicker overzichtDatePicker;

	private Hotel hotel = Hotel.getHotel();

	public void initialize() {
		hotelnaamLabel.setText("Boekingen hotel " + hotel.getNaam());
		overzichtDatePicker.setValue(LocalDate.now());
		toonBoekingen();
	}

	public void toonVorigeDag(ActionEvent actionEvent) {
		LocalDate dagEerder = overzichtDatePicker.getValue().minusDays(1);
		overzichtDatePicker.setValue(dagEerder);
	}

	public void toonVolgendeDag(ActionEvent actionEvent) {
		LocalDate dagLater = overzichtDatePicker.getValue().plusDays(1);
		overzichtDatePicker.setValue(dagLater);
	}

	public void nieuweBoeking(ActionEvent actionEvent) {
		try {
			String fxmlPagina = "/" + this.getClass().getPackageName().replaceAll("\\.", "\\/") + "/Boekingen.fxml";
			System.out.println(fxmlPagina);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPagina));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Boeking");
			stage.setScene(new Scene(root));
			stage.showAndWait();
			this.initialize();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void toonBoekingen() {
		ObservableList<String> boekingen = FXCollections.observableArrayList();
		for (Boeking boeking : hotel.getBoekingen()) {
			int kamer = boeking.getKamer().getKamerNummer();
			LocalDate datumStart = boeking.getAankomstDatum();
			if (!this.overzichtDatePicker.getValue().equals(datumStart))
				continue;
			LocalDate datumEind = boeking.getVertrekDatum();
			String naam = boeking.getBoeker().getNaam();
			boekingen.add(String.format("Kamer %s van %s tot %s, op naam van: %s", kamer, datumStart, datumEind, naam));
		}
		boekingenListView.setItems(boekingen);
	}
}