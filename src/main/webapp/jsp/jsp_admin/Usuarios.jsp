<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Academico Usuario buscado</title>
    <link rel="stylesheet" href="../../css/stylescomun.css">
    <link rel="stylesheet" href="../../css/stylesmedia.css">
</head>
<body>
    <c:import url= "/header.jsp"/>
    
    <table class="tablecontainer">
        <tr>
            <th class = "tableth">
                nombre
            </th>
            <th class = "tableth">
                apellido
            </th>
            <th class = "tableth">
                mail
            </th>
            <th class = "tableth">
                clave
            </th>
            <th class = "tableth">
                estado
            </th>
            <th class = "tableth">
            </th>
        </tr>
        <c:forEach items="${usuarios}" var = "usuario">
        <tr>
            <td class="tabletd">
                ${usuario.nombre}
            </td>
            <td class="tabletd">
                ${usuario.apellido}
            </td>
            <td class="tabletd">
                ${usuario.email}
            </td>
            <td class="tabletd">
                ${usuario.contrasenia}
            </td>
            <td class="tabletd">
                ${usuario.estado}
            </td>
            <c:choose>
                <c:when test = "${usuario.estado == 'ACTIVO'}">
                    <td class="tabletd">
                        <a href="borrarUsuario?idUsuario=${usuario.idUsuario}&origen=mostrarUsuarios">desactivar</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td class="tabletd">
                        <a href="activarUsuario?idUsuario=${usuario.idUsuario}&origen=mostrarUsuarios">activar</a>
                    </td>
                </c:otherwise>
            </c:choose>
            <td class="tabletd">
                <a href="usuarioEditar?idUsuario=${usuario.idUsuario}&origen=mostrarUsuarios">editar</a>
            </td>
        </tr>
        </c:forEach>
    </table>

    <a class = "asalir" href="./BuscarUsuario.jsp">Salir</a>
 
</body>
</html>