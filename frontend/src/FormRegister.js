import React, {Component} from 'react';
import './Form.css'

const URL = "http://localhost:8080/user";

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
            <form onSubmit={this.handleSubmit}>
                <label for="login">Login</label>
                <input type="text" id="login" name="login"/>

                <label for="firstName">First name</label>
                <input type="text" id="firstName" name="firstName"/>

                <label for="lastName">Last name</label>
                <input type="text" id="lastName" name="lastName"/>

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
        let req = new XMLHttpRequest();
        req.open("POST", URL);
        req.onreadystatechange = () => {
            console.log(req.status);
        };
        req.setRequestHeader("Content-Type", "application/json");
        req.send(JSON.stringify({
            login: "user",
            firstName: "name",
            lastName: "surname",
            password: "123",
        }));
    }

    async _handleSubmit(evt) {
        evt.preventDefault();
        let resp = await fetch(URL, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                login: "user",
                firstName: "name",
                lastName: "surname",
                password: "123",
            }),
        });
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