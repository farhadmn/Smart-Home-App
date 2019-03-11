  
  
  var licht_switch = document.getElementById("schlafzimmer_licht");
  var steckdose_switch = document.getElementById("schlafzimmer_steckdose");
  var alarmanlage_switch = document.getElementById("global_alarmanlage");
  var bekleidung_switch = document.getElementById("schlafzimmer_bekleidung");
  
  

  var jalousien_range = document.getElementById("jalousien_range");
  var output_jalousien = document.getElementById("jalousien_wert");
  
  
  
  
  //updateLicht
  function updateLichtStatus() {
		  
	 
	  var licht = licht_switch.checked;
	  
	  
	  database.ref('schlafzimmer/').update({
			  
		    licht: licht
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }
  
  
  
  //updateSteckdose
  function updateSteckdoseStatus() {
		  
	 
	 var steckdose_status = steckdose_switch.checked;
	  
	  
	  database.ref('schlafzimmer/').update({
			  
		   steckdose: steckdose_status
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }  
  
  
      //updateJalousien
  function updateJalousienStatus() {
		  
	
	    var jalousien_range_value = jalousien_range.value;
	  
	  
	  database.ref('schlafzimmer/').update({
			  
		jalousien: jalousien_range_value
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }
  
  
  
    //updatebekleidungstatus
  function updateBekleidungStatus() {
		  
	 
	 var schlafzimmer_bekleidung = bekleidung_switch.checked;
	  
	  
	  database.ref('schlafzimmer/').update({
			  
		    schlafzimmer_bekleidung:schlafzimmer_bekleidung
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }  
  
  
  
  //

  
  
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
	  
	
	 jalousien_wert.innerHTML = this.value;
	updateJalousienStatus();
	 
  }

  

  document.addEventListener("DOMContentLoaded", function() {
  

  
  loadData();
  
  });// JavaScript Document