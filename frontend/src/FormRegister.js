import React, {Component} from 'react';
import './Form.css'

class FormRegister extends Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onClickLogin = this.onClickLogin.bind(this);
    }

    render() {
        return (
            <form method="post" action="">
                <label for="login">Login</label>
                <input type="text" id="login" name="login"/>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
                <label for="confirmPassword">Confirm password</label>
                <input type="password" id="confirmPassword" name="confirmPassword"/>
                <input type="submit" value="Register"/>
                <br/>
                <a href="" class="hint" onClick={this.onClickLogin}>Already registered?</a>
            </form>
        );
    }

    handleSubmit(evt) {
        evt.preventDefault();
        /** TODO **/
    }

    handleChange(evt) {
        this.setState({[evt.target.name]: evt.target.value});
    }

    onClickLogin(evt) {
        evt.preventDefault();
        this.props.setScreen("login");
    }
}

export default FormRegister