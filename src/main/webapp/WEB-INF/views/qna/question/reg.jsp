<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/resource/js/ckeditor/ckeditor.js"></script>

<script>
    var editor;
    CKEDITOR.on( 'instanceReady', function( ev ) {
        editor = ev.editor;
        document.getElementById( 'readOnlyOn' ).style.display = '';
        editor.on( 'readOnly', function() {
            document.getElementById( 'readOnlyOn' ).style.display = this.readOnly ? 'none' : '';
            document.getElementById( 'readOnlyOff' ).style.display = this.readOnly ? '' : 'none';
        });
    });
   
    function toggleReadOnly( isReadOnly ) {
        editor.setReadOnly( isReadOnly );
    }
</script>
	<main class="main">
<form action="?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		<fieldset>
	<div class="view_wrap">
		<table class="board">
			<tr>
				<th>제목</td>
				<td><input type="text" name="title" /></td>
			</tr>
		</table>
		<div class="page_num">
		<textarea class="ckeditor" name="content" rows="20" cols="60"></textarea>

		</div>
		<div class="reg-button">
				<input type="submit" value="등록" />
					<a href="../qna/list">취소</a>
		</div>

	</div>

<script type="text/javascript">
    CKEDITOR.replace('Contents',{
            toolbar: 'Full',
            uiColor: '#9AB8F3',
        }
    );
</script>


</fieldset>
</form>
</main>