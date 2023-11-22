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
        <form action="addCalificacion" class="form" method="post">
            <h4 class = formheader>Agregar curso</h4>
            <div class="container">
                <c:if test="${hayErrorVacio}">
                        <p>${VacioError}</p>
                </c:if>
                <div class="formgroup">
                    <label for="nombre" class="formlabel">Nombre: </label>
                    <input type="text" id="nombre" name="nombre" class="forminput">
                    <span class="formspan"></span>
                </div>
                <br>
                <div class="formgroup">
                    <label for="apellido" class="formlabel">Apellido </label>
                    <input type="text" id="apellido" name="apellido" class="forminput">
                    <span class="formspan"></span>
                </div>
                <c:if test="${hayErrorAlumno}">
                        <p>${alumnoError}</p>
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
                <div class="formgroup">
                    <label for="estado" class="formlabel">Materia </label>
                    <input type="text" id="materia" name="materia" class="forminput">
                    <span class="formspan"></span>
                </div>
                <c:if test="${hayErrorMateria}">
                        <p>${materiaError}</p>
                </c:if>
                <br>
                <input type="submit" value="Ingresar" class="formingresar">
            </div>
        </form>
    </body>
</html>