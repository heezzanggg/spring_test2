<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">

 <!--  메뉴목록  -->
  <table id="menu">
  <tr> 
  <c:forEach var="menu"  items="${ menuList }" >
   <td>
     <a href="/Pds/List?menu_id=${menu.menu_id}&nowpage=${pagePdsVo.nowpage}&pagecount=${pagePdsVo.pagecount}&pagegrpnum=${pagePdsVo.pagegrpnum}">
      ${ menu.menu_name }
     </a> 
   </td>
  </c:forEach>
   <td>
    <a href="/Pds/List?nowpage=${pagePdsVo.nowpage}&pagecount=${pagePdsVo.pagecount}&pagegrpnum=${pagePdsVo.pagegrpnum}">
      All
    </a> 
  </td>
  </tr>
  </table>