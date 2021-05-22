
 function addFeild(myself, element_id) {
	var element = document.getElementById(element_id);
	var table = document.getElementById(element_id + '_table');
	
	// Manipulating DOM
	element.removeChild(myself);
	element.appendChild(table);
 }
 
