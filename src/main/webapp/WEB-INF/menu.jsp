<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 08:37
  To change this template use File | Settings | File Templates.
--%>

<c:if test="${utilisateursCno_utilisateurs == null}">
    <header>
        <ul>
            <li><a href="Connexion">Se connecter-S'inscrire</a></li>
        </ul>
    </header>
</c:if>
${utilisateursCno_utilisateurs}
<c:if test="${utilisateursCno_utilisateurs != null}">
    <header>
        <ul>
            <li><a href="Accueil">Enchères</a></li>
            <li><a href="NouvelleVente">Vendre un article</a></li>
            <li><a href="Profil">Mon profil</a></li>
            <li><a href="Deconnexion">Déconnexion</a></li>
        </ul>
    </header>
</c:if>

