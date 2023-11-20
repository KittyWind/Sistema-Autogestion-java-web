<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Academico Menu Admin</title>
    <link rel="stylesheet" href="../../css/stylescomun.css">
    <link rel="stylesheet" href="../../css/stylesmedia.css">
</head>
<body>
    <c:import url= "/header.jsp"/>
    <nav class="navcontainer">
        <h4 class="navh4">Usuario: Administrador</h4>
        <a href="mostrarUsuarios" class="nava">Gestionar Usuarios</a>
        <a href="./CursosAdmin.html" class="nava">Gestionar Cursos</a>
        <a href="../../index.jsp" class="nava">Salir</a>
    </nav>
</body>
</html>