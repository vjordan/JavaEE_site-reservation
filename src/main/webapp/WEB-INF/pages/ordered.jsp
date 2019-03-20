<%@include file="/WEB-INF/layouts/header.jspf" %>
<%@include file="/WEB-INF/components/connection.jspf" %>
<%@include file="/WEB-INF/components/subscription.jspf" %>
<%@page import="imt.mmpj.storage.model.Ordered" %>
<%@page import="java.util.List;" %>

	<%
	List<List<Object>> vListeOrdered = ((List<List<Object>> ) request.getAttribute("listeListeOrder"));

	if(!vListeOrdered.isEmpty())
	{
		for(List<Object> vListe : vListeOrdered)
		{
		%>
		   <div class="container">
		  	<h3 class="text-primary">Commande numéro : <%= ((Ordered)vListe.get(0)).getNumeroCommande() %></h3><hr/>        
		    <table class="table table-striped">
		     <thead>
			      <tr>
			        <th>Produit</th>
			        <th>Quantité</th>
			        <th>Prix total</th>
			      </tr>
		    </thead>
		    <tbody>
		    	<% 
		    	for(Object vOrder : vListe)
		    	{
		    	%>
		    	  <tr>
			        <td><%=((Ordered) vOrder).getItem().getName() %></td>
			        <td><%=((Ordered) vOrder).getQuantite() %></td>
			        <td><%=((Ordered) vOrder).getTotal() %></td>
			      </tr>
			    <%
		    	}
		    	%>
		     </tbody>
		   </table>
		 </div>
		<%
		}	
	}
	else
	{
	%>
	<h1 style="margin-top:7%" class="text-danger text-center">Pas de commande effectuée</h1>
	<%
	}
	%>

 
<%@include file="/WEB-INF/layouts/footer.jspf" %>