<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/layouts/header.jspf" %>
<%@include file="/WEB-INF/components/connection.jspf" %>
<%@include file="/WEB-INF/components/subscription.jspf" %>


<div class="container">
    <h1>
        Ajouter un produit
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

    <form class="form-horizontal" action="catalogAdd" method="POST">

        <i class="text-danger">Champs requis *</i>

        <div class="form-group">
            <label class="control-label col-sm-2" for="imageurl">Image url :</label>
            <div class="col-sm-10">
                <input type="text" name="imageurl" class="form-control" id="imageurl" value="${imageurl}">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="code">Code : <i class="text-danger">*</i></label>
            <div class="col-sm-10">
                <input type="text" name="code" class="form-control" id="code" value="${code}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Nom : <i class="text-danger">*</i></label>
            <div class="col-sm-10">
                <input type="text" name="name" class="form-control" id="name" value="${name}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="price">Prix : <i class="text-danger">*</i></label>
            <div class="col-sm-10">
                <input type="number" name="price" step="0.01" class="form-control" min="0" value="${price}" id="price" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="quantity">Quantité : <i class="text-danger">*</i></label>
            <div class="col-sm-10">
                <input type="number" name="quantity" class="form-control" min="0" value="${quantity}" id="quantity" required>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-6 col-sm-6">
                <button type="submit" class="btn btn-primary">Créer</button>
            </div>
        </div>
    </form>

</div>

<%@include file="/WEB-INF/layouts/footer.jspf" %>