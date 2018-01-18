import React, { Component } from 'react';
import './Form.css'

class FormLogin extends Component {
  render() {
    return (
      <form class="login" method="post" action="">
	    <label for="login">Login</label>
        <input type="text" id="login" name="login" />
	    <label for="password">Password</label>
        <input type="password" id="password" name="password" />
        <input type="submit" value="Login" />
      </form>
    );
  }
}

export default FormLogin