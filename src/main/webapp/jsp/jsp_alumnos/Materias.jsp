<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Academico Materias</title>
    <link rel="stylesheet" href="../../css/stylescomun.css">
    <link rel="stylesheet" href="../../css/stylesmedia.css">
</head>
<body>
    <c:import url= "/header.jsp"/>

    <table class="tablecontainer">
        
        <tr>
            <th class = "tableth">
                Materia
            </th>
        </tr>
        <c:choose>
            <c:when test="${empty materias}">
                <tr>
                    <td colspan="3" class="tabletd">
                        No hay materias
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${materias}" var="materia">
                    <tr>
                        <td class="tabletd">
                            <a href="materiaDetalle?idMateria=${materia.idMateria}&origen=materias">${materia.nombre}</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>

    <a class = "asalir" href="./MenuAlumno.jsp">Salir</a>
 
</body>
</html>