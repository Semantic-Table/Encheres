<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 07/07/2022
  Time: 08:37
  To change this template use File | Settings | File Templates.
--%>
<header>
    <div class="parentMenu">
        <a href="Accueil" class="lienLogo"><img id="logo" src="img/juge.png" alt=""></a>
        <div class="menu">


            <c:if test="${utilisateursC.no_utilisateur == null}">


                <a href="Connexion" class="lienMenu">
                    <div class="liendivMenu">
                        Se connecter-S'inscrire
                    </div>
                </a>


            </c:if>
            <c:if test="${utilisateursC.no_utilisateur != null}">
                <p class="liendivMenu">Vous possedez ${utilisateursC.credit} points</p>
                <a href="Accueil" class="lienMenu">
                    <div class="liendivMenu"><img src="img/magnifying-glass-solid.svg" width="20" height="20" alt="" class="iconMenu">Enchères</div>
                </a>
                <c:if test="${utilisateursC.actif}">
                    <a href="NouvelleVente" class="lienMenu">
                        <div class="liendivMenu"><img src="img/sack-dollar-solid.svg" width="20" height="20" alt="" class="iconMenu">Vendre un article</div>
                    </a>
                </c:if>
                <a href="Profil" class="lienMenu">
                    <div class="liendivMenu"><img src="img/user-solid.svg" width="20" height="20" alt="" class="iconMenu">Mon profil</div>
                </a>
                <a href="Deconnexion" class="lienMenu">
                    <div class="liendivMenu"><img src="img/arrow-right-from-bracket-solid.svg" width="20" height="20" alt="" class="iconMenu">Déconnexion</div>
                </a>

            </c:if>
        </div>

    </div>
    <div class="reseaux">
        <a href="#" class="lienMenu">
            <img src="img/twitter-brands.svg"
                 width="40"
                 height="40"
                 class="svg"
                 type="image/svg+xml">
        </a>
        <a href="#" class="lienMenu">
            <img src="img/facebook-brands.svg"
                 width="40"
                 height="40"
                 class="svg"
                 type="image/svg+xml">
        </a>
        <a href="#" class="lienMenu">
            <img src="img/instagram-brands.svg"

                 class="svg"
                 type="image/svg+xml"/>
        </a>
    </div>
</header>

