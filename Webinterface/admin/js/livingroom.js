  
  //Variable definieren
  var heizung_range = document.getElementById("heizung_range");
  var alarmanlage_switch = document.getElementById("global_alarmanlage");
  var tv_switch = document.getElementById("tv_switch");
  var output_heizung = document.getElementById("heizung_wert");

  
  
  //updateTVStatus
  function updateTVStatus() {
		  
	 
	  var tv_value = tv_switch.checked;
	  
	  
	  database.ref('wohnzimmer/').update({
			  
		  tv: tv_value
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }
  
  
 
  
  
    //updateHeizungStatus
  function updateHeizungStatus() {
		  
	
	  heizungsValue = heizung_range.value;
	  
	  
	  database.ref('wohnzimmer/').update({
			  
		  heizung: heizungsValue
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }
  
  
  //Daten aus der Datenbank aufrufen
  function loadData(){
  
	  var wohnzimmerRef = firebase.database().ref("wohnzimmer/");
	  
	  wohnzimmerRef.once('value').then(function(snapshot) {
		  
		  var heizung_status = (snapshot.val().heizung);
		  var tv_status = (snapshot.val().tv);
		  
		  heizung_range.value=heizung_status;
		  output_heizung.innerHTML=heizung_status;
		  tv_switch.checked = tv_status;	
		  
	  });
	  
	  
	  var Global_valuesRef = firebase.database().ref("Global_values/");
	  
	  Global_valuesRef.once('value').then(function(snapshot) {
		  
		  var alarm_switch_value = (snapshot.val().alarmanlage);
		  alarmanlage_switch.checked = alarm_switch_value;
		  
	  });
	  
  }
  
  //Wunschtemperatur anzeigen
  heizung_range.oninput = function() {
	  
	
	 output_heizung.innerHTML = this.value;
	 updateHeizungStatus();
	 
  }
  
  //wird aufgerufen, wenn die Seite geladen ist.. und ruft die Funktion loadData()
  document.addEventListener("DOMContentLoaded", function() {
	  
	  //window.open("https://www.w3schools.com"); 
	  
	  loadData();
	  
  });// JavaScript Document