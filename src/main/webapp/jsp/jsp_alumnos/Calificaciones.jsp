<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sistema Academico Calificaciones</title>
        <link rel="stylesheet" href="../../css/stylescomun.css">
        <link rel="stylesheet" href="../../css/stylesmedia.css">
    </head>
    <body>
        <c:import url= "/header.jsp"/>

        <table class="tablecontainer">
            <!-- <caption class = "tablecaption">Calificaciones del alumno</caption> -->
            <tr>
                <th class = "tableth">
                    Materia
                </th>
                <th class = "tableth">
                    Examen
                </th>
                <th class = "tableth">
                    Nota
                </th>
            </tr>
            <c:choose>
                <c:when test="${empty calificaciones}">
                    <tr>
                        <td colspan="3" class="tabletd">
                            No hay calificaciones
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${calificaciones}" var="calificacion">
                        <tr>
                            <c:forEach items="${materias}" var="materia">
                                <c:if test="${materia.idMateria == calificacion.idMateria}">
                                    <td class="tabletd">
                                        <a href="materiaDetalle?idMateria=${materia.idMateria}&origen=calificaciones">${materia.nombre}</a>
                                    </td>
                                </c:if>
                            </c:forEach>
                            <td class="tabletd">
                                ${calificacion.numExamen}
                            </td>
                            <td class="tabletd">
                                ${calificacion.nota}
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>

        <a class = "asalir" href="./MenuAlumno.jsp">Salir</a>
    </body>
</html>