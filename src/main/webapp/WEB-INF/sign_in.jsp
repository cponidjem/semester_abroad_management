
<form method="post" action="sign_in_action">
	<div class="row">
		<div class="five columns">
			<label for="role">I am ...</label> 
			<select class="u-full-width" id="role" name="role">
				<option value="student">an INSA student</option>
				<option value="insa">an INSA representative</option>
				<option value="university">a partner university</option>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="five columns">
			<label for="login">Username/E-mail</label> 
			<input class="u-full-width" type="text" id="login" name="login">
		</div>
	</div>
	<div class="row">
		<div class="five columns">
			<label for="password">Password</label>
			<input class="u-full-width" type="password"  id="password" name="password">
		</div>
	</div>
	<div class="row">
		<div class="two columns">
			<input class="button-primary" type="submit" value="Sign in">
		</div>
	</div>
</form>