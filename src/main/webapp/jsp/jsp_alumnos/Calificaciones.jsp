<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Academico Calificaciones</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <header>
        <div class="logo">
            <img src="../img/escudo.jpg" alt="escudo de la escuela" class="logoimg">
            <h1 class="logotext">Escuela Tecnica N° 28 Republica Francesa</h1>
            <img src="../img/escudo.jpg" alt="escudo de la escuela" class="logoimg">
        </div>
    </header>
    <br>
    <hr>

    <table class="tablecontainer">
        <caption class = "tablecaption">Calificaciones del alumno</caption>
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
        <tr>
            <td class="tabletd">
                Lengua
            </td>
            <td class="tabletd">
                1
            </td>
            <td class="tabletd">
                8
            </td>
        </tr>
        <tr>
            <td class="tabletd">
                Lengua
            </td>
            <td class="tabletd">
                2
            </td>
            <td class="tabletd">
                6
            </td>
        </tr>
        <tr>
            <td class="tabletd">
                Historia
            </td>
            <td class="tabletd">
                1
            </td>
            <td class="tabletd">
                7
            </td>
        </tr>
        <c:forEach items="listaDeCalificaciones" var="calificacion">
            <tr>
                <td class="tabletd">
                    ${calificacion.materia}
                </td>
                <td class="tabletd">
                    ${calificacion.numExamen}
                </td>
                <td class="tabletd">
                    ${calificacion.nota}
                </td>
            </tr>
        </c:forEach>
    </table>

    <a class = "asalir" href="../jsp_alumnos/MenuAlumno.html">Salir</a>
</body>
</html>