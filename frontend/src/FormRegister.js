import React, { Component } from 'react';
import './Form.css'

class FormRegister extends Component {
  render() {
    return (
      <form class="register" method="post" action="">
	    <label for="login">Login</label>
        <input type="text" id="login" name="login" />
	    <label for="password">Password</label>
        <input type="password" id="password" name="password" />
		<label for="confirmPassword">Confirm password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" />
        <input type="submit" value="Register" />
      </form>
    );
  }
}

export default FormRegister