
// Wrap it in a Closure
	
var lot_number = 0; 

 function AddLot(element) {
	
	lot_number++;
	
	try {
			var grandParent = element.parentElement.parentElement.parentElement;
			
			var row = document.createElement("tr");
			
			var hidden_1 = document.createElement("td");
			hidden_1.style.display = "none";
			
			var lotId = document.getElementById("lotId");
			var lotId  = lotId.cloneNode(true);
			lotId.id = "list" + lot_number + ".id";
			lotId.name = "list[" + lot_number + "].id";
			lotId.value = "";
			hidden_1.appendChild(lotId);	
			
			var hidden_2 = document.createElement("td");
			hidden_2.style.display = "none";
			
			var inventoryID = document.getElementById("inventoryID");
			var inventoryID  = inventoryID.cloneNode(true);
			inventoryID.id = "list" + lot_number + ".inventory.id";
			inventoryID.name = "list[" + lot_number + "].inventory.id";
			inventoryID.value = "";
			hidden_2.appendChild(inventoryID);
			
			var column_0 = document.createElement("td");
			var item = document.getElementById("item");
			var input_0  = item.cloneNode(true);
			input_0.id = "list" + lot_number + ".inventory.item";
			input_0.name = "list[" + lot_number + "].inventory.item";
			
			var column_1 = document.createElement("td");
			var name = document.getElementById("name");
			var input_1  = name.cloneNode(true);
			input_1.id = "list" + lot_number + ".inventory.detail.name";
			input_1.name = "list[" + lot_number + "].inventory.detail.name";
			
			var column_2 = document.createElement("td");
			var color = document.getElementById("color");
			var input_2  = color.cloneNode(true);
			input_2.id = "list" + lot_number + ".inventory.detail.color";
			input_2.name = "list[" + lot_number + "].inventory.detail.color";
			
			var column_3 = document.createElement("td");
			var size = document.getElementById("size");
			var input_3  = size.cloneNode(true);
			input_3.id = "list" + lot_number + ".inventory.detail.size";
			input_3.name = "list[" + lot_number + "].inventory.detail.size";
			
			
			var column_4 = document.createElement("td");
			var types = document.getElementById("types");
			var select  = types.cloneNode(true);
			select.id = "list" + lot_number + ".inventory.detail.type";
			select.name = "list[" + lot_number + "].inventory.detail.type";
			
			var column_5 = document.createElement("td");
			var quantity = document.getElementById("quantity");
			var input_5  = quantity.cloneNode(true);
			input_5.id = "list" + lot_number + ".quantity";
			input_5.name = "list[" + lot_number + "].quantity";
			
			var column_6 = document.createElement("td");
			var input_6 = document.createElement("input");
			input_6.type = "text";
			input_6.id = "list" + lot_number + ".amount";
			input_6.name = "list[" + lot_number + "].amount";
			
			var column_7 = document.createElement("td");
			var Agent = document.getElementById("agents");
			var input_7 = Agent.cloneNode(true);
			input_7.name = "list[" + lot_number + "].agent";
			
			var column_8 = document.createElement("td");
			var Add = document.getElementById("Add");
			var input_8 = Add.cloneNode(true);
			
			var column_9 = document.createElement("td");
			var Remove = document.getElementById("Remove");
			var input_9 = Remove.cloneNode(true);
			input_9.onClick="RemoveLot(row);";
			
			column_0.appendChild(input_0);
			column_1.appendChild(input_1);
			column_2.appendChild(input_2);
			column_3.appendChild(input_3);
			column_4.appendChild(select);
			column_5.appendChild(input_5);
			column_6.appendChild(input_6);
			column_7.appendChild(input_7);
			column_8.appendChild(input_8);
			column_9.appendChild(input_9);
			
			row.appendChild(hidden_1); 
			row.appendChild(hidden_2);
			
			row.appendChild(column_0); 
			row.appendChild(column_1);
			row.appendChild(column_2);
			row.appendChild(column_3);
			row.appendChild(column_4);
			row.appendChild(column_5);
			row.appendChild(column_6);
			row.appendChild(column_7);
			row.appendChild(column_8);
			row.appendChild(column_9);
			
			grandParent.appendChild(row);
		}
		catch (error)
		{
			alert("Error while Adding Lot : " + error);
		}
	
 }
 
 function RemoveLot(element)
 {
	try
	{
		element.parentNode.removeChild(element);
	}
	catch (error)
	{
		alert("Error while Removing Lot : " + error);
	}
}
 


			    			
/*<tr>
	<td style="display:none"> <input id="list0.id" name="list[0].id" type="hidden"> </td>
	<td style="display:none"> <input id="list0.inventory.id" name="list[0].inventory.id" type="hidden"> </td>
	<td> <input type="text" id="list0.inventory.name" name="list[0].inventory.detail.name" value=""> </td>
	<td> <input type="text" id="list0.inventory.color" name="list[0].inventory.detail.color" value=""> </td>
	<td> <input type="text" id="list0.inventory.size" name="list[0].inventory.detail.size" value=""> </td>
	<td> <input type="text" id="list0.inventory.type" name="list[0].inventory.detail.type" value=""> </td>
	<td> <input type="text" value="" id="list0.quantity" name="list[0].quantity"> </td>
 
</tr>*/