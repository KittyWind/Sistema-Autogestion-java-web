<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Academico Menu Profesor</title>
    <link rel="stylesheet" href="../../css/stylescomun.css">
    <link rel="stylesheet" href="../../css/stylesmedia.css">
</head>
<body>
    <c:import url= "/header.jsp"/>

    <nav class="navcontainer">
        <h4 class="navh4">${userLogueado.apellido}, ${userLogueado.nombre}</h4>
        <a href="cursos" class="nava">Gestionar cursos</a>
        <a href="calificacion" class="nava">Gestionar Calificaciones</a>
        <a href="logout" class="nava">Salir</a>
    </nav>
</body>
</html>