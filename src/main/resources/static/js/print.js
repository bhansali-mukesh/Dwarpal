
	function Print(name) {

		try
		{
			var elements = document.getElementsByName(name);
			if(elements.length == 0) { alert(elements[0].innerHTML);}
			elements.forEach(element => {
				var inner = element.parentNode.innerHTML;
				w = window.open();
				w.document.write(inner);
				w.print();
				w.close();
			});
		}
		catch (error)
		{
			//alert(error);
			window.print();
		}
		
	}