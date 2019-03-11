  //initialize variables
  
  
   var alarmanlage_switch = document.getElementById("global_alarmanlage");
  
  
	//Firebase config

	var config = {
  
	  apiKey: "",
  	  authDomain: "",
  	  databaseURL: "",
      projectId: "",
      storageBucket: "",
      messagingSenderId: ""
  
	};
  
	firebase.initializeApp(config);
   
   // Get a reference to the database service
  
  var database = firebase.database();
 
  
  firebase.auth().onAuthStateChanged(function(user) {
  
  
  
  var loginhtml= "login.html";
  
  var path = window.location.pathname;
  
  var page = path.split("/").pop();   // page=login.html
  
  var n = page.localeCompare(loginhtml); // check if page is login.html
  
   
  
   // check if user_logged
  
	if (user) {
  
	  // User is signed in.
  
  
  
	  var user = firebase.auth().currentUser;

	  if(user != null){

		var email_id = user.email;

		if (n==0){ // page==login.html and user is logged

		 window.location.replace("/admin/index.html");
  
	  }

	  }
  
  
  
	} else {
  
	  // No user is signed in.

  
  if (n==0){ // if user is no signed in and page== login.html
  
	  // break;
  
	  }
  
	  else {
  
		  
  
		  window.location.replace("/admin/login.html"); // if user is no signed in and page != login.html
  
	  }
  
  
  
	}
  
  });
  
  
  
  function login(){
  
	var userEmail = document.getElementById("email_field").value;
  
	var userPass = document.getElementById("password_field").value;
  
  
  
	firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
  
	  // Handle Errors here.
  
	  var errorCode = error.code;
  
	  var errorMessage = error.message;
  
	  window.alert("Error : " + errorMessage);
  
  
  
	  // ...
  
	});
  
  
  
  }
  
 
  
  function logout(){
  
	firebase.auth().signOut();
  
  }
  
  
  //Funktion für die Datenspeicherung
	function updateAlarmanlageStatus() {
			
	   
		var alarmValue = alarmanlage_switch.checked;
		
		
		database.ref('Global_values/').update({
				
			 alarmanlage: alarmValue
		
			}, function(error) {
			
			if (error) {
					
				} else {
						
			}
			
		});
	   
	}
  
  
  
  
  
