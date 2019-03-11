var drucker = document.getElementById("drucker");
var drucker_status= document.getElementById("drucker").value;

function saveWData() {
	
	database.ref('office/').update({
		
		drucker: drucker_status
		
		
		}, function(error) {
		
		if (error) {
			
			// The write failed...
			saveok.innerHTML= "Die &Auml;nderungen konnten nicht gespeichert  !";
			
			} else {
			
			// Data saved successfully!
			
			saveok.innerHTML= "&Auml;nderungen erfolgreich gespeichert !";
			
		}
		
	});
	
	
	setDefaultValue();	
	
}


function setDefaultValue() {
	
	var value=true;
	
	database.ref('Settings/').set({
		
		OfficeDefaultValue:value
		
	});
	
	
	
}


function loadData(){

	var OfficeRef = firebase.database().ref("office/");
	OfficeRef.once('value').then(function(snapshot) {
	var drucker_value = (snapshot.val().drucker);
   	drucker.value = drucker_value;
		
		
	});
		
}

document.addEventListener("DOMContentLoaded", function() {
	
	//window.open("https://www.w3schools.com"); 
	
	loadData();
	
});// JavaScript Document