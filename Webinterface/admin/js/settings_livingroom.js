var heizung_range = document.getElementById("heizung_range");
var alarmanlage_switch = document.getElementById("global_alarmanlage");
var tv_switch = document.getElementById("tv_switch");
var output_heizung = document.getElementById("heizung_wert");


function saveWData() {

	
	var  heizung_range_value =  heizung_range.value;
	var tv_value = tv_switch.checked;
	
	database.ref('wohnzimmer/').set({
		
		heizung: heizung_range_value,
		
		tv: tv_value

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
	
	saveok.innerHTML= alarmanlage_switch.checked;
	var alarmanlage_value = alarmanlage_switch.checked;
	
	database.ref('Global_values/').set({
		
		alarmanlage:alarmanlage_value
			
		
	});
			
}

function setDefaultValue() {
	
	
	var value= true;
	database.ref('Settings/').update({
	LivingroomDefaultValue:value
	
	});
		
}


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


	heizung_range.oninput = function() {
		output_heizung.innerHTML = this.value;
	
}


document.addEventListener("DOMContentLoaded", function() {
	
	//window.open("https://www.w3schools.com"); 
	
	loadData();
	
});// JavaScript Document