<%-- 
    Document   : Calificaciones.jsp
    Created on : 7 nov. 2023, 19:25:23
    Author     : Francisco
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <!-- <caption class = "tablecaption">Cursos del profesor</caption> -->
        <tr>
            <th class="tableth">
                alumnos
            </th>
            <th class="tableth">
                materia
            </th>
            <th class="tableth">
                examen
            </th>
            <th class="tableth">
                nota
            </th>
            <th class="tableth">
            </th>
            <th class="tableth">
            </th>
        </tr>
        <c:forEach items="${alumnos}" var="alumno">
            <c:forEach items="${calificaciones}" var="calificacion">
                <c:if test="${alumno.idAlumno == calificacion.idAlumno && alumno.estado == 'ACTIVO'}">
                    <c:forEach items="${materias}" var="materia">
                        <c:if test="${materia.idMateria == calificacion.idMateria}">
                            <tr>
                                <td class="tabletd">
                                    ${alumno.nombre} ${alumno.apellido}
                                </td>
                                <td class="tabletd">
                                    ${materia.nombre}
                                </td>
                                <td class="tabletd">
                                    ${calificacion.numExamen}
                                </td>
                                <td class="tabletd">
                                    ${calificacion.nota}
                                </td>
                                <td class="tabletd">
                                    <a href="califEditar?idAlumno=${alumno.idAlumno}&idMateria=${materia.idMateria}&idCalificacion=${calificacion.idCalificacion}&origen=calificacion">editar</a>
                                </td>
                                <%-- <td class="tabletd">
                                    <a href="califBorrar?idCalificacion=${calificacion.idCalificacion}&origen=calificacion">borrar</a>
                                </td> --%>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </c:forEach>
        <tr>
            <td class="tabletd" colspan="4">
                <a href="addCalificacion">agregar calificacion</a>
            </td>
        </tr>
    </table>
    <a class="asalir" href="./MenuProfesor.jsp">Salir</a>
</body>
</html>

