<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/layouts/header.jspf" %>
<%@include file="/WEB-INF/components/connection.jspf" %>
<%@include file="/WEB-INF/components/subscription.jspf" %>

<div class="container">

    <h1>
        Articles dans le panier
    </h1>

<%	
    if (!request.getSession().getAttribute("taillePanier").equals(0)) {
%>
		<form action="basket" method="POST">
		    <table class="table table-striped">
		        <thead class="thead-dark">
		        <tr>
		            <th class="text-center">Nom</th>
		            <th class="text-center">Prix</th>
		            <th class="text-center">Quantité</th>
		            <th class="text-center"></th>
		            <th class="text-center"></th>
		        </tr>
		        </thead>
		        <tbody>
		        <%
		        int compteur = 1;
		        String nom = "quantiteVoulue";
		        String code = "codeVoulue";
		        %>
		        <c:forEach var="item" items="${basket}">
		        	<%  String nomModifie = nom + Integer.toString(compteur);
		        		String codeModifie = code + Integer.toString(compteur);
		        	%>
		            <tr class="text-center" id="${item.code}">
		                <td>${item.name}</td>
		                <td>${item.cost}</td>
		                <td>
		                	<input class="form-control" type="number" min="1" max="${item.quantity}" style="width:150px;margin-left:40%" name="<%= nomModifie %>" id="${item.name}" value=1 ></input>
		                	<input type="hidden" name="<%= codeModifie %>" value="${item.code}"></input>
		                </td>
		                <td>
		                    <button  type="button" class="btn btn-warning" id="btnMoins" onclick="Moins('${item.name}')">
		                        <i class="fa fa-minus"></i>
		                    </button>
		                    <button type="button" class="btn btn-success" id="btnPlus" onclick="Plus('${item.name}','${item.quantity}')">
		                        <i class="fa fa-plus"></i>
		                    </button>
		                </td>
		                <td>
		                    <button type="button" class="btn btn-danger" id="btnAjoutPanier" onclick="RetirePanier('${item.code}')">
		                        <i class="fa fa-trash"></i>
		                    </button>
		                </td>
		            </tr>
		            <% compteur++; %>
		        </c:forEach>
		        </tbody>
		    </table>
		    
			<button type="submit" class="btn btn-primary" id="btnValidationPanier" style="margin-left:45%">Commander</button>
		</form>
<%
	}
    else
    {
    %>
    <h1 style="margin-top:7%" class="text-danger text-center">Panier vide</h1>
    <%
    }
%>
		

</div>

<script>

	function RetirePanier(idItem){
	    $.ajax({
		    url: "basket",
		    type: "POST",
		    data: {
		    	id: idItem,
		    	action: "remove",
		    	commander: "false"
		    },
		    success: function(){
		    	$("#"+idItem).remove();
		    	location.reload();
		    	var size = '<%= request.getSession().getAttribute("taillePanier") %>';
		    	var label = $("#basketSize") ;
		    	label.text(""+size);
		    },
		    error: function(){
		    	//alert("error"); 
		    }
		});
	}
	
	function Plus(item,quantiteDispo) {
		var tmp = parseInt(document.getElementById(item).value);
		if (tmp<quantiteDispo) {
			document.getElementById(item).value = tmp+1;
		}
	}
	
	function Moins(item) {
		var tmp = parseInt(document.getElementById(item).value);
		if (tmp>1) {
			document.getElementById(item).value = tmp-1;
		}
	}

</script>

<%@include file="/WEB-INF/layouts/footer.jspf" %>