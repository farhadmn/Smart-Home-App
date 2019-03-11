  
  //Variable definieren
var drucker = document.getElementById("drucker");
var drucker_status= document.getElementById("drucker").value;
  
  
  //updatedrucker
  
  function updateDruckerStatus() {
		  
	 var drucker_value= drucker.value;
	 

	  database.ref('office/').update({
			  
		  drucker: drucker_value
	  
		  }, function(error) {
		  
		  if (error) {
				  
			  } else {
					  
		  }
		  
	  });
	 
  }
  
   
  //Daten aus der Datenbank aufrufen
  function loadData(){

var OfficeRef = firebase.database().ref("office/");
OfficeRef.once('value').then(function(snapshot) {
var drucker_value = (snapshot.val().drucker);
drucker.value = drucker_value;
 

});

}
  
 
  //wird aufgerufen, wenn die Seite geladen ist.. und ruft die Funktion loadData()
  document.addEventListener("DOMContentLoaded", function() {
	  
	  //window.open("https://www.w3schools.com"); 
	  
	  loadData();
	  
  });// JavaScript Document