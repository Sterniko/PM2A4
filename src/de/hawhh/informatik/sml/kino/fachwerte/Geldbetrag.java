package de.hawhh.informatik.sml.kino.fachwerte;

//CONSTRUCTORS
/**
 * Klasse zur Präsentation von int in der Form eines Geldbetrags, der EE,CC entspricht, also EUROBETRAG, CENTBETRAG.
 * Die Repräsentation wird als String dargeboten und bietet zudem grundlegende mathematische Funktionen an 
 * @author Sternke
 *
 */
public class Geldbetrag {
private int eurocents;


/**
 * Konstruktor eines leeren Geldbetrages. Hier wird per default ein "00,00" erzeugt.
 * @ensure das Objekt ist benutzbar und repräsentiert "00,00".
 */
public Geldbetrag() {
	eurocents = 0;
}

/**
 * Konstruktor eines Geldbetrages mit Hilfe eines Integer-Objektes
 * @param geldbetrag das Integer Objekt des Geldbetrages
 * @require der Integer ist nicht null
 * @require der Wert ist nicht negativ
 * @ensure der Wert des Geldbetrages entspricht dem des Integers
 */
public Geldbetrag(Integer geldbetrag) {
	assert(geldbetrag != null): "Vorbedingung Verletzt nicht null";
	eurocents = geldbetrag.intValue();
}
/**
 * Konstruktor eines Geldbetrages, der dem Betrag in Eurocents entspricht
 * @param eurocents
 * @require der Geldbetrag ist nicht negativ
 * @ensure ein Objekt, das dem Geldbetrag entspricht
 */
public Geldbetrag(int eurocents) {
	assert(eurocents >=0) : "Vorbedingung verletzt geldbetrag nicht negativ";
	this.eurocents = eurocents;
}
/**
 * Konstruktor um einen Geldbetrag aus einem String zu erzeugen.
 * Der String muss der Form einer Zahl entsprechen oder einer Kommazahl. Es wird maximal die zweite Nachkommastelle berücksichtigt.
 * @param geldbetrag String repräsenation des Geldbetrages
 * @require geldbetrag nicht null und geldbetrag hat die geforderte Form.
 * @ensure ein Geldbetrag-Objekt, welches dem String enspricht
 */
public Geldbetrag(String geldbetrag) {
	assert(geldbetrag != null): "Vorbedingung verletzt geldbetrag nicht null";	
	try {
		Integer i = Integer.parseInt(geldbetrag);
		eurocents = i.intValue();
	}catch(NumberFormatException e) {
		try {
			Double d = Double.parseDouble(geldbetrag);
			d= d*100;
			eurocents = d.intValue();
		}catch(NumberFormatException ex) {
			assert(false) : "Vorbedingung verletzt -> Geldbetrag entspricht nicht Format";
		}
	}
}

//MAIN STUFF

/**
 * String repräsentation des Geldbetrages in der Form "EE..,CC".
 * @return ein String, der dem Geldbetrag entspricht
 */
public String getStringRepräsentation() {
	char[] cs = Integer.valueOf(eurocents).toString().toCharArray();
	String CC = "";
	String EE = "";
	//"normale länge : letzte 2 einträge sind cent, der rest euro
	if(cs.length >= 3) {
		CC += cs[cs.length-2];
		CC += cs[cs.length-1];		
		for(int i =0; i<cs.length-2; i++) {
			EE += cs[i];
		}
		//falls nur zwei einträge vorhanden, sind beide cent
	}else if (cs.length == 2) {
		EE = "00";
		for(int j =0;j< cs.length; j++) {
			CC += cs[j];
		}		
	}else{
		//nur ein Eintrag
			CC = "0" + cs[0];
		}
	
	//zusammenfügen
	return EE + "," + CC;
	}
	
//MATHS

//OBJECT MATH-METHODS

public void add(Geldbetrag a) {
	
}

public void add(int eurocents) {
	
}

public void add(Integer eurocents) {
	
}

public void add(String geldbetrag) {
	
}

public void sub(Geldbetrag a) {
	
}

public void sub(int eurocents) {
	
}

public void sub(Integer eurocents) {
	
}

public void sub(String geldbetrag) {
	
}

public void multiply(int multiplier) {
	
}



//CLASS MATH-METHODS
/**
 * Gibt ein neues Geldbetragsobjekt zurück, welches dem ersten Geldbetrag minus dem zweiten entspicht
 * @param a der Geldbetrag, von dem Abgezogen werden soll
 * @param b der Geldbetrag, der abzuziehen ist
 * @return Ein neues Geldbetragsobjekt, welches a-b enspricht
 * @require a nicht null
 * @require a ist valide, also nicht negativ
 * @require b nicht null
 * @require b ist valide, also nicht negativ
 * @ensure der Betrag enspricht a - b; 
 */
public static Geldbetrag geldbetragSubtraktion(Geldbetrag a, Geldbetrag b) {
	assert( a != null) :"Vorbedingung verletzt a nicht null";
	assert( b != null) : "Vorbedingung verletzt b nicht null";
	assert(a.getValidity()) :"Vorbedingung verletzt a nicht valide";
	assert (b.getValidity()) : "Vorbedingung verletzt b nicht valide";
	assert(a.getGeldbetrag()>= b.getGeldbetrag()) : "Vorbedingung verletzt a größer b";
	return new Geldbetrag(a.getGeldbetrag() -b.getGeldbetrag());
}

/**
 * Gibt die positive Differenz zweier Geldbeträge in der Form eines Geldbetrages zurück, entspricht dem mathematischen |a-b|
 * @param a der erste Geldbetrag	
 * @param b der zweite Geldbetrag
 * @return eine neuer geldbetrag, welcher der Differenz beider Beträge entspricht
 * @require a ist nicht null
 * @require a ist valide
 * @require b ist nicht null
 * @require b ist valide
 * @ensure ein neues Geldbetragsobjekt, das der Differenz entspricht
 */
public static Geldbetrag geldbetragDifferenz(Geldbetrag a, Geldbetrag b) {
	int neuerBetrag;
	if(a.getGeldbetrag() > b.getGeldbetrag()) {
		 neuerBetrag = a.getGeldbetrag() - b.getGeldbetrag();
	}else {
		neuerBetrag = b.getGeldbetrag() - a.getGeldbetrag();
	}
	return new Geldbetrag(neuerBetrag);
}

/**
 * Ermöglicht es zwei Geldbeträge aufeinander zu addieren
 * @param a der erste Geldbetrag
 * @param b der zweite Geldbetrag
 * @return ein Geldbetragobjekt, welches dem Wert von a+b entspricht
 * @require a nicht null
 * @require a ist valide
 * @require b nicht null
 * @require b ist valide
 * @ensure das Objekt enspricht a+b
 */
public static Geldbetrag geldbetragAddition(Geldbetrag a, Geldbetrag b) {
	assert( a != null) :"Vorbedingung verletzt a nicht null";
	assert( b != null) : "Vorbedingung verletzt b nicht null";
	assert(a.getValidity()) :"Vorbedingung verletzt a nicht valide";
	assert (b.getValidity()) : "Vorbedingung verletzt b nicht valide";
	return new Geldbetrag(a.getGeldbetrag() + b.getGeldbetrag());
}

public static Geldbetrag geldbertagMultiply(Geldbetrag a, int multiplier) {
	return null;
}

//UTILITY
/**
 * Erlaubt das Ändern des Wertes des GEldbetrages
 * @param geldbetrag der neue Geldbetrag
 * @require der Geldbetrag darf nicht negativ sein
 * @ensure der neue Wert entspricht dem gegebenen
 */
public void setGeldbetrag(int geldbetrag) {
	assert (geldbetrag>=0): "Vorbedingung verletzt geldbetrag nicht negativ";
	this.eurocents = geldbetrag;
}

/**
 * Gibt den momentanen Geldbetrag zurück
 * @return den Wert des Geldbetrages in Eurocents
 */
public int getGeldbetrag() {
	return eurocents;
}




/**
 * Gibt an, ob der Geldbetrag valide ist, also nicht negativ
 * @return false wenn der Geldbetrag kleiner ist, als 0. Sonst true
 */
public boolean getValidity() {
	return eurocents >= 0;
}

}


