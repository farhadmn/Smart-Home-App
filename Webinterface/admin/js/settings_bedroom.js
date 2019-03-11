  
  
  var licht_switch = document.getElementById("schlafzimmer_licht");
  var steckdose_switch = document.getElementById("schlafzimmer_steckdose");
  var alarmanlage_switch = document.getElementById("global_alarmanlage");
  var bekleidung_switch = document.getElementById("schlafzimmer_bekleidung");
  
  
  
  var jalousien_range = document.getElementById("jalousien_range");
  var output_jalousien = document.getElementById("jalousien_wert");
  
  
  
  function saveData() {
	  
	  var licht = licht_switch.checked;
	  var steckdose = steckdose_switch.checked;
	  var jalousien_range_value = jalousien_range.value;
	  var schlafzimmer_bekleidung = bekleidung_switch.checked;
	  
	  
	  
	  database.ref('schlafzimmer/').set({
		  
		  licht: licht,
		  steckdose: steckdose,
		  jalousien: jalousien_range_value,
		  schlafzimmer_bekleidung:schlafzimmer_bekleidung
		  
		  
		  }, function(error) {
		  
		  if (error) {
			  
			  // The write failed...
			  
			  saveok.innerHTML= "Die &Auml;nderungen konnten nicht gespeichert  !";
			  
			  } else {
			  
			  // Data saved successfully!
			  
			  saveok.innerHTML= "&Auml;nderungen erfolgreich gespeichert !";
			  
		  }
		  
	  });
	  
  saveAlarmanalge();
  setDefaultValue();
  
  }
  
  
  function saveAlarmanalge() {
  
  
  var alarmanlage_value = alarmanlage_switch.checked;
  
  
  database.ref('Global_values/').set({
  
  alarmanlage:alarmanlage_value
  
  });
  
  }
  
  
  function setDefaultValue() {
  
  var value=true;
  database.ref('Settings/').update({
  BedRoomDefaulValue:value
  
  });
  
  
  }
  
  
  function loadData(){
  
  var schlafzimmerRef = firebase.database().ref("schlafzimmer/");
  
  schlafzimmerRef.once('value').then(function(snapshot) {
  
  var licht_status = (snapshot.val().licht);
  var steckdose_status = (snapshot.val().steckdose);
  var bekleidung_switch_value = (snapshot.val().schlafzimmer_bekleidung);
  var jalousien_range_value = (snapshot.val().jalousien);
  
  
  licht_switch.checked = licht_status;
  steckdose_switch.checked = steckdose_status;
  
  bekleidung_switch.checked=bekleidung_switch_value;
  output_jalousien.innerHTML=jalousien_range_value;
  jalousien_range.value=jalousien_range_value;
  
  });
  
  
  var Global_valuesRef = firebase.database().ref("Global_values/");
  Global_valuesRef.once('value').then(function(snapshot) {
  var alarm_switch_value = (snapshot.val().alarmanlage);
  alarmanlage_switch.checked = alarm_switch_value;
  
  
  
  });
  
  
  
  }
  
  jalousien_range.oninput = function() {
  output_jalousien.innerHTML = this.value;
  
  }
  
  document.addEventListener("DOMContentLoaded", function() {
  
  //window.open("https://www.w3schools.com"); 
  
  loadData();
  
  });// JavaScript Document