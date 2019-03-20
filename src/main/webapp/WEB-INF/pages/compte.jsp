<%@include file="/WEB-INF/layouts/header.jspf" %>

<div class="container">
<%
if(request.getAttribute("exception")!=null)
{
%>
<div class="alert alert-danger">
  <strong>Erreur :</strong> ${exception}
</div>
<%
}
%>

<h1 class="text-primary" style="margin-bottom:5%">Formulaire pour modifier les informations du compte</h1>

 <form class="form-horizontal" action="compte" method="POST">
  <div class="form-group">
    <label class="control-label col-sm-2" for="email">Email :</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="email" placeholder="${utilisateur.mail }" disabled>
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="email">Nom :</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="email" placeholder="${utilisateur.nom }" disabled>
    </div>
  </div>
   <div class="form-group">
    <label class="control-label col-sm-2" for="email">Prénom :</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="email" placeholder="${utilisateur.prenom }" disabled>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Mot de passe :</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="password" id="pwd" value="${utilisateur.motDePasse }">
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Mot de passe (Confirmation):</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="passwordConfirm" id="pwd" value="${utilisateur.motDePasse }">
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Adresse :</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="adresse" id="pwd" value="${utilisateur.adresse }">
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Code postal :</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="cp" id="pwd" value="${utilisateur.codePostal }">
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Ville :</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="ville" id="pwd" value="${utilisateur.ville }">
    </div>
  </div>
    <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Pays :</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="pays" id="pwd" value="${utilisateur.pays }">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-6 col-sm-6">
      <button type="submit" class="btn btn-primary">Modifier</button>
    </div>
  </div>
</form> 

</div>

<%@include file="/WEB-INF/layouts/footer.jspf" %>