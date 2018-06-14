package de.hawhh.informatik.sml.kino.werkzeuge.abrechnung;
import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.abrechnung.AbrechnungswerkzeugUI;
import javafx.scene.control.Button;


public class Abrechnungswerkzeug extends ObservableSubwerkzeug {
	private int _preis;
	private AbrechnungswerkzeugUI _uiVerk;

	public Abrechnungswerkzeug() {
		_uiVerk = new AbrechnungswerkzeugUI();
	}


	public void aktivieren() {
		_uiVerk.get_preisLabel().setText(preisTextPos());
		gibFunktion();
		_uiVerk.zeigeAn();
	}

	public int get_preis() {
		return _preis;
	}

	public void set_preis(int _preis) {
		this._preis = _preis;
	}

	public AbrechnungswerkzeugUI get_uiVerk() {
		return _uiVerk;
	}

	public void set_uiVerk(AbrechnungswerkzeugUI _uiVerk) {
		this._uiVerk = _uiVerk;
	}

	private void gibFunktion() {
		Button b = _uiVerk.get_abschlussButton();

		b.setOnAction(e -> {
			System.out.println("You clicked the Abschluss-button");
			if (hasPaidEnough()) {
				_uiVerk.showFinishedWindow();
			} else {
				_uiVerk.showMoreMoneyWindow();
			}
		});

		_uiVerk.get_abbrechenButton().setOnAction(e -> {
			System.out.println("You clicked the Abbrechen-button");
			_uiVerk.showAreYouSureWindow();
		});

		_uiVerk.get_bezahlenButton().setOnAction(e -> {
			System.out.println("You clicked the bezahlen-button");
			calculateChange();
			_uiVerk.get_textField().clear();
		});

	}

	private void calculateChange() {
		System.out.println(_uiVerk.get_textField().getText());
		try {
			Integer a = Integer.decode(_uiVerk.get_textField().getText());
			if (a < 0)
				throw new NumberFormatException();
			_preis -= a;

			if (_preis > 0)
				_uiVerk.get_preisLabel().setText(preisTextPos());
			else
				_uiVerk.get_preisLabel().setText(preisTextNeg());

		} catch (NumberFormatException e) {
			_uiVerk.showErrorWindowWrongInput();
		}
	}

	private String preisTextPos() {
		return new String("Der Preis beträgt: " + _preis + " Eurocent");
	}

	private String preisTextNeg() {
		return new String("Das Wechselgeld beträgt: " + (-_preis) + " Eurocent");
	}

	private boolean hasPaidEnough() {
		return _preis <= 0;
	}


	public boolean properly() {
		return _uiVerk.properly();
	}

}
