<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="post" action="universities">
	<div class="row">
		<div class="six columns">
			<label for="keywords">Keywords</label> 
			<input class="u-full-width" type="text" id="keywords" name="keywords">
		</div>
		<div class="three columns">
			<label for="country">Country</label> 
			<select class="u-full-width" id="country" name="country">
			<c:forEach var="country" items="${countries}">
				<option value="${country}">${country}</option>
			</c:forEach>
			</select>
		</div>
		<div class="three columns">
			<label for="field">Field</label> 
			<select class="u-full-width" id="field" name="field">
				<c:forEach var="field" items="${fields}">
					<option value="${field}">${field}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="two columns">
			<input class="button-primary" type="submit" value="Search">
		</div>
	</div>
</form>
<ul>
<c:forEach var="university" items="${universities}">
	<li><a href="${param.path}/university?id=${university.id}">${university.name}</a></li>
</c:forEach>
</ul>