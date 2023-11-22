<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : Alumnos
    Created on : 2 nov. 2023, 00:45:48
    Author     : Francisco
--%>
 
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Alumnos del Curso</title>
    <link rel="stylesheet" href="../../css/stylescomun.css">
    <link rel="stylesheet" href="../../css/stylesmedia.css">
</head>

<body>
    <c:import url="/header.jsp" />
    <table class="tablecontainer">
        <caption class="tablecaption">Alumnos del Curso</caption>
        <tr>
            <th class="tableth">
                Nombre
            </th>
            <th class="tableth">
                Apellido
            </th>
            <th class="tableth">
                Email
            </th>
        </tr>

        <c:choose>
            <c:when test="${empty alumnos}">
                <tr>
                    <td class="tabletd" colspan="4">
                        No hay alumnos
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${alumnos}" var="alumno">
                    <c:if test="${alumno.idCursada == curso.idCursada && alumno.estado == 'ACTIVO'}">
                        <tr>
                            <td class="tabletd">
                                ${alumno.nombre}
                            </td>
                            <td class="tabletd">
                                ${alumno.apellido}
                            </td>
                            <td class="tabletd">
                                ${alumno.email}
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <a class="asalir" href="./MenuProfesor.jsp">Salir</a>
</body>

</html>