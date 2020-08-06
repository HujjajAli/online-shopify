
<p class="lead">Shop Name</p>
   <div class="list-group">
   		<c:forEach items="${categories}" var="r">	
        	<a href="${contextRoot}/category?category=${r.id}" id="a_${r.name}" class="list-group-item">${r.name}</a>
        </c:forEach>
   </div>