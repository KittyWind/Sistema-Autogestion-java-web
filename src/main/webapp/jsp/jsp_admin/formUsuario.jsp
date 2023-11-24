<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sistema Academico Administrar usuarios</title>
        <link rel="stylesheet" href="../../css/stylescomun.css">
        <link rel="stylesheet" href="../../css/stylesmedia.css">
    </head>
    <body>
        <c:import url= "/header.jsp"/>
        <c:choose>
           <c:when test="${Path == '/jsp/jsp_admin/usuarioEditar'}">
                <form action="editarUsuario?idUsuario=${idUsuario}" class="form" method="post">
                    <h4 class = formheader>Editar usuario</h4>
                    <div class="container">
                        <c:if test="${hayError}">
                                <p>${mensajeError}</p>
                        </c:if>
                        <br>
                        <div class="formgroup">
                            <label for="nombre" class="formlabel">nombre </label>
                            <input type="text" id="nombre" name="nombre" class="forminput">
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <div class="formgroup">
                            <label for="apellido" class="formlabel">apellido </label>
                            <input type="text" id="apellido" name="apellido" class="forminput">
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <div class="formgroup">
                            <label for="email" class="formlabel">email </label>
                            <input type="text" id="email" name="email" class="forminput">
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <div class="formgroup">
                            <label for="clave" class="formlabel">clave </label>
                            <input type="text" id="clave" name="clave" class="forminput">
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <div class="formgroup">
                            <label for="estado" class="formlabel">estado </label>
                            <select name="estado">
                                <option value="ACTIVO">activo</option>
                                <option value="INACTIVO">inactivo</option>  
                            </select>
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <input type="submit" value="Ingresar" class="formingresar">
                    </div>
                </form>
           </c:when>
           <c:otherwise>
                <%-- <form action="insertarCalif?idAlumno=${idAlumno}" class="form" method="post">
                    <h4 class = formheader>Agregar calficacion</h4>
                    <div class="container">
                        <c:if test="${hayError}">
                                <p>${mensajeError}</p>
                        </c:if>
                        <br>
                        <div class="formgroup">
                            <label for="numExamen" class="formlabel">Numero de examen </label>
                            <input type="number" id="numExamen" name="numExamen" class="forminput">
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <div class="formgroup">
                            <label for="nota" class="formlabel">Nota </label>
                            <input type="number" id="nota" name="nota" class="forminput">
                            <span class="formspan"></span>
                        </div>
                        <br>
                        <select name="materias">
                            <c:forEach items="${materias}" var="materia">
                                <option value="${materia.idMateria}">${materia.nombre}</option>
                            </c:forEach>
                            
                        </select>
                        <input type="submit" value="Ingresar" class="formingresar">
                    </div>
                </form> --%>
           </c:otherwise>
        </c:choose>
        
    </body>
</html>