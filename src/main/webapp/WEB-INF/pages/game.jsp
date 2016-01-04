<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<style type="text/css">
body {
	font-family: sans-serif;
	font-weight: normal;
	margin: 10px;
	color: #999;
	background-color: #eee;
}

form {
	margin: 40px 0;
}

div {
	clear: both;
	margin: 0 50px;
}

label {
  width: 300px;
  border-radius: 3px;
  border: 1px solid #D1D3D4
}

/* hide input */
input.radio:empty {
	margin-left: -999px;
}

/* style label */
input.radio:empty ~ label {
	position: relative;
	float: left;
	line-height: 2.5em;
	text-indent: 3.25em;
	margin-top: 2em;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

input[type=submit] {
    padding:5px 15px; 
    background:#ccc; 
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px;
    border: 1px solid 
}

input[type="submit"]:hover{
    border: 1px solid #999;color:#000;
}

input.radio:empty ~ label:before {
	position: absolute;
	display: block;
	top: 0;
	bottom: 0;
	left: 0;
	content: '';
	width: 2.5em;
	background: #D1D3D4;
	border-radius: 3px 0 0 3px;
}

/* toggle hover */
input.radio:hover:not(:checked) ~ label:before {
	content:'\2714';
	text-indent: .9em;
	color: #C2C2C2;
}

input.radio:hover:not(:checked) ~ label {
	color: #888;
}

/* toggle on */
input.radio:checked ~ label:before {
	content:'\2714';
	text-indent: .9em;
	color: #9CE2AE;
	background-color: #4DCB6D;
}

input.radio:checked ~ label {
	color: #777;
}

/* radio focus */
input.radio:focus ~ label:before {
	box-shadow: 0 0 0 3px #999;
}
</style>

<script type="text/javascript">

function process(e) {
	document.getElementById('gameForm').submit();
	}

</script>
</head>
<body>
	<h1>Welcome to Rock paper scissors game!</h1>
	
	<c:if test="${gameForm.gameType == null}">
	<h4>Please choose game type ${gameForm.gameType}</h4>
		<form:form method="post" action="setGameType" commandName="gameForm">
		
			<c:forEach var="gameType" items="${gameTypes}" >
				<div>
					<form:radiobutton path="gameType" value="${gameType}" cssClass="radio" id="${gameType}" onclick="process(this)"/>
					<label for="${gameType}">${gameType}</label>
				</div>
			</c:forEach>			
		</form:form>
	</c:if>
	
	<c:if test="${gameForm.gameType != null}">
		<h4>Game type ${gameForm.gameType}</h4>	
	</c:if>

	<c:if test="${gameForm.gameType != null && gameForm.gameMode == null}">	
		<form:form method="post" action="setGameMode" commandName="gameForm">
		 	<form:hidden path="gameType"/>
	
	
			<p>Please choose game mode</p>
			<c:forEach var="gameMode" items="${gameModes}">
				<div>
					<form:radiobutton path="gameMode" value="${gameMode}" cssClass="radio" id="${gameMode}" onclick="process(this)"/>
					<label for="${gameMode}">${gameMode}</label>
				</div>
			</c:forEach>
		</form:form>
	</c:if>

	<c:if test="${gameForm.gameMode != null}">
		<h4>Game mode ${gameForm.gameMode}</h4>	
	</c:if>
	
	<c:if test="${gameForm.gameMode != null && gameForm.playerOne == null}">		
		<form:form method="post" action="setPlayers" commandName="gameForm">
	 		<form:hidden path="gameType"/>
	 		<form:hidden path="gameMode"/>
	 		
			<c:choose>
			    <c:when test="${gameForm.gameMode == 'PLAYER_COMPUTER'}">
			       <form:label path="playerOne">Player one</form:label><form:input path="playerOne" />
			    </c:when>
			    <c:when test="${gameForm.gameMode == 'PLAYER_PLAYER'}">
			        <p><form:label path="playerOne">Player one</form:label>
			        	<form:input path="playerOne" />
			        	<form:errors path="playerOne" cssclass="error"></form:errors>
			        </p> 
	         		<p><form:label path="playerTwo">Player two</form:label><form:input path="playerTwo" /></p>
			    </c:when>
			    <c:otherwise>
			        <script type="text/javascript">
			        	document.getElementById('gameForm').submit();
			        </script>
			    </c:otherwise>
			</c:choose>
		<input type="submit" value="Next">
		</form:form>	
	</c:if>		

	<c:if test="${gameForm.playerOne != null}">
		<h4>Players ${gameForm.playerOne} vs. ${gameForm.playerTwo}</h4>
	</c:if>
	
	<c:choose>
	    <c:when test="${gameForm.gameMode == 'PLAYER_PLAYER'}">

			<c:if test="${gameForm.gameMode != null && gameForm.playerOne != null && gameForm.playerOneMove == null}">
				<form:form method="post" action="setPlayerMove" commandName="gameForm">
			 		<form:hidden path="gameType"/>
			 		<form:hidden path="gameMode"/>
			 		<form:hidden path="playerOne"/>
			 		<form:hidden path="playerTwo"/>
			
					<p>${gameForm.playerOne} - Please choose move</p>
					<c:forEach var="gamePossibleMove" items="${gamePossibleMoves}">
						<div>
							<form:radiobutton path="playerOneMove" value="${gamePossibleMove}" cssClass="radio" id="${gamePossibleMove}" onclick="process(this)"/>
							<label for="${gamePossibleMove}">${gamePossibleMove}</label>
						</div>
					</c:forEach>			
				</form:form>
			</c:if>
			
			<c:if test="${gameForm.gameMode != null && gameForm.playerOneMove != null && gameForm.playerTwoMove == null}">	
				<form:form method="post" action="setPlayerMove" commandName="gameForm">
			 		<form:hidden path="gameType"/>
			 		<form:hidden path="gameMode"/>
			 		<form:hidden path="playerOne"/>
			 		<form:hidden path="playerTwo"/>
			 		<form:hidden path="playerOneMove"/>
			
					<p>${gameForm.playerTwo} - Please choose move</p>
					<c:forEach var="gamePossibleMove" items="${gamePossibleMoves}">
						<div>
							<form:radiobutton path="playerTwoMove" value="${gamePossibleMove}" cssClass="radio" id="${gamePossibleMove}" onclick="process(this)"/>
							<label for="${gamePossibleMove}">${gamePossibleMove}</label>
						</div>
					</c:forEach>
				</form:form>
			</c:if>
	       
	    </c:when>
	    <c:when test="${gameForm.gameMode == 'PLAYER_COMPUTER'}">
	       
	       	<c:if test="${gameForm.gameMode != null && gameForm.playerOne != null && gameForm.playerOneMove == null}">
				<form:form method="post" action="setPlayerMove" commandName="gameForm">
			 		<form:hidden path="gameType"/>
			 		<form:hidden path="gameMode"/>
			 		<form:hidden path="playerOne"/>
			 		<form:hidden path="playerTwo"/>
			
					<p>${gameForm.playerOne} - Please choose move</p>
					<c:forEach var="gamePossibleMove" items="${gamePossibleMoves}">
						<div>
							<form:radiobutton path="playerOneMove" value="${gamePossibleMove}" cssClass="radio" id="${gamePossibleMove}" onclick="process(this)"/>
							<label for="${gamePossibleMove}">${gamePossibleMove}</label>
						</div>
					</c:forEach>			
				</form:form>
			</c:if>
			
	    </c:when>
	    <c:when test="${gameForm.gameMode == 'COMPUTER_COMPUTER'}">
	    	<form:form method="post" action="setPlayerMove" commandName="gameForm">
	    		<form:hidden path="gameType"/>
			 	<form:hidden path="gameMode"/>
			 	<form:hidden path="playerOne"/>
			 	<form:hidden path="playerTwo"/>
		        Computer ready to play  
		        <input type="submit" value="GO!">
	        </form:form>
	    </c:when>
	</c:choose>
	

	
	<c:if test="${gameForm.gameMode != null && gameForm.playerOneMove != null && gameForm.playerTwoMove != null}">
		
		<c:if test="${gameForm.result.winner != 0}">
			<h3 style="color:#00FF00">Winner is</h3>  
		</c:if>
		<c:if test="${gameForm.result.winner == 0}">
			<h3 style="color:#00FF00">Draw</h3>
		</c:if>
		
		<div id="div-one"><h3>${gameForm.playerOne} &#8680;  ${gameForm.playerOneMove}</h3></div> 
		<h2>vs.</h2> 
		<div id="div-two"><h3>${gameForm.playerTwo} &#8680;  ${gameForm.playerTwoMove}</h3></div> 
		
		<c:choose>
			    <c:when test="${gameForm.result.winner == 1}">
			       <script type="text/javascript">
			       		document.getElementById("div-one").style.color="#00FF00"
			       </script>
			    </c:when>
			    <c:when test="${gameForm.result.winner == 2}">
			       <script type="text/javascript">
			       		document.getElementById("div-two").style.color="#00FF00"
			       </script>
			    </c:when>
			    <c:otherwise>
			       
			    </c:otherwise>
			</c:choose>
		
	</c:if>
	
	<c:if test="${gameForm.gameMode != null && gameForm.playerOne != null && gameForm.playerTwo != null}">
		<br>
		<form:form method="post" action="setPlayerMove" commandName="gameForm">
			<form:hidden path="gameType"/>
			<form:hidden path="gameMode"/>
			<form:hidden path="playerOne"/>
			<form:hidden path="playerTwo"/>
			<input type="submit" value="Replay">
		</form:form>
	</c:if>
	
	<p><br><br><br><a href=".">Start again</a></p>

</body>
</html>