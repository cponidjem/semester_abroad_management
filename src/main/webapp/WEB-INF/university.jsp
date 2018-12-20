<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>${message}</h3>
<c:if test="${not empty university}">
	<h4>${university.country}</h4>
	<h4>Courses</h4>
	<ul>
	<c:forEach var="field" items="${university.fields}">
		<li>
			${field.name}
			<ul>
			<c:forEach var="program" items="${field.programs}">			
					<li>
						${program.name}
						<ul>
						<c:forEach var="course" items="${program.courses}">			
								<li>${course.name}: ${course.description}</li>			
						</c:forEach>
						</ul>
					</li>			
			</c:forEach>
			</ul>
		</li>
	</c:forEach>
	</ul>
</c:if>