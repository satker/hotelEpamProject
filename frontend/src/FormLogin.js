import React, {Component} from 'react';
import './Form.css'

const URL_FORM = "http://localhost:8080/login";
const URL_LOGIN = "http://localhost:8080/app-login";

export default class FormLogin extends Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onClickRegister = this.onClickRegister.bind(this);
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label for="login">Login</label>
                <input type="text" id="login" name="login" onChange={this.handleChange}/>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" onChange={this.handleChange}/>
                <input type="submit" value="Login"/>
                <br/>
                <a href="" class="hint" onClick={this.onClickRegister}>Do not have account?</a>
            </form>
        );
    }

    async handleSubmit(evt) {
        evt.preventDefault();
        //console.log(await (await fetch(URL_FORM)).text());
        let resp = await fetch(URL_LOGIN, {
            method: "POST",
            body: {
                app_username: "aleksey",
                app_password: "m123",
            },
        });
        console.log(resp.status);
        let text = await resp.text();
        console.log(text);
        //this.props.setScreen("list_of_users"); // For debug only
    }

    onClickRegister(evt) {
        evt.preventDefault();
        this.props.setScreen("register");
    }

    handleChange(evt) {
        this.setState({[evt.target.name]: evt.target.value});
    }
}