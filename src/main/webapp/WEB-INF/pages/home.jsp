<%@include file="/WEB-INF/layouts/header.jspf" %>

<%
	if(request.getSession().getAttribute("utilisateur")==null) {
%>
	<%@include file="/WEB-INF/components/connection.jspf" %>
	<%@include file="/WEB-INF/components/subscription.jspf" %>
<%
	}
%>


<%

if(request.getAttribute("success")!=null)
{
%>
<div class="alert alert-success">
  <strong>Succ�s :</strong> ${success}
</div>
<%
}
%>
<div class="container">
	

  <div class="jumbotron">
    <h1>Association des amateurs de vin</h1>
    <p>Nous sommes une association qui propose � la vente des cuv�es m�connues</p>
    <%
    if(request.getSession().getAttribute("utilisateur")==null)
    {
    %>
    <p>Connectez-vous ou inscrivez-vous et allez dans le catalogue pour voir et commander nos diff�rentes cuv�es</p>
    <%
    }
    else
    {
    %>
    <p>Allez dans le catalogue pour voir et commander nos diff�rentes cuv�es</p>
    <%
    }
    %>
    
    <div class="text-center"><img src="https://img4.hostingpics.net/pics/470114vin.jpg" class="img-thumbnail img-responsive"> </div>
  </div>  
  
</div>

 
<%@include file="/WEB-INF/layouts/footer.jspf" %>