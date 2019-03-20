<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/layouts/header.jspf" %>
<%@include file="/WEB-INF/components/connection.jspf" %>
<%@include file="/WEB-INF/components/subscription.jspf" %>


<div class="container">

    <h1>
        Catalogue des articles
    </h1>

    <%
        if(request.getAttribute("exception")!=null)
        {
    %>
    <div class="alert alert-danger">
        <strong>Erreur :</strong> ${exception}
    </div>
    <%
        }
        if(request.getAttribute("success")!=null)
        {
    %>
    <div class="alert alert-success">
        <strong>Succès :</strong> ${success}
    </div>
    <%
        }
    %>

    <table class="table table-striped" id="catalog">
        <thead class="thead-dark">
        <tr>
            <th class="text-center">Illustration</th>
            <th class="text-center">Nom</th>
            <th class="text-center">Prix</th>
            <th class="text-center">Quantité disponible</th>
            <%
       			if(request.getSession().getAttribute("utilisateur")!=null) {
			%>
            	<th class="text-center">Ajouter</th>
            <%
       			}
			%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr class="text-center">
                <td>
                    <img style="width: 100px; height: 100px;" class="img-thumbnail" src="${item.imageUrl == null || item.imageUrl == "" ? "https://vignette.wikia.nocookie.net/arrow-france/images/e/ea/No-foto.png/revision/latest?cb=20160615100846&path-prefix=fr" : item.imageUrl}" alt="image produit">
                </td>
                <td>${item.name}</td>
                <td>${item.cost}</td>
                <td class="${item.quantity == 0 ? "text-danger" : ""}">${item.quantity == 0 ? "Rupture" : item.quantity}</td>
	            <%
       				if(request.getSession().getAttribute("utilisateur")!=null) {
				%>
	                <td>
	                    <button class="btn btn-primary" ${item.quantity == 0 ? "disabled" : ""} id="btnAjoutPanier" onclick="AjoutPanier('${item.code}')">
	                        <i class="fa fa-cart-arrow-down"></i>
	                    </button>
	                </td>
	            <%
       				}
				%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script>
    $(document).ready(function() {
        $('#catalog').DataTable();
    } );
    
	function AjoutPanier(idItem){
	    $.ajax({
		    url: "basket",
		    type: "POST",
		    data: {
		    	id: idItem ,
		    	action:"add",
		    	commander: "false"
		    },
		    success: function(){
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
    
</script>

<%@include file="/WEB-INF/layouts/footer.jspf" %>