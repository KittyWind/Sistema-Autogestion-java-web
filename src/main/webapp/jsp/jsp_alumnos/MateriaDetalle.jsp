<%-- 
    Document   : MateriaDetalle
    Created on : 17 nov. 2023, 15:23:19
    Author     : manue
--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sistema Academico ${materia.nombre}</title>
        <link rel="stylesheet" href="../../css/stylescomun.css">
        <link rel="stylesheet" href="../../css/stylesmedia.css">
    </head>
    <body>
        <c:import url= "/header.jsp"/>

        <table class="tablecontainer">
            <tr>
                <th colspan="2" class="tableth">
                    ${materia.nombre}
                </th>
            </tr>
            <tr>
                <th class = "tableth">
                    Examen
                </th>
                <th class = "tableth">
                    Nota
                </th>
            </tr>
            <c:forEach items="${calificaciones}" var="calificacion">
                <c:if test="${calificacion.idMateria == materia.idMateria}">
                    <tr>
                        <td class = "tabletd">
                            ${calificacion.numExamen}
                        </td>
                        <td class = "tabletd">
                            ${calificacion.nota}
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${param.origen == 'materias'}">
                <a class = "asalir" href="materias">Volver</a>
            </c:when>
            <c:when test="${param.origen == 'calificaciones'}">
                <a class = "asalir" href="calificaciones">Volver</a>
            </c:when>
        </c:choose>
    </body>
</html>