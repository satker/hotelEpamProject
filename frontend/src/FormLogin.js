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

        let oReq = new XMLHttpRequest();
        oReq.open("POST", URL_LOGIN, true);
        oReq.onreadystatechange = () => {
            console.log(oReq.status);
            if( oReq.readyState === 4 ) {
                console.log(oReq.responseText);
            }
        };
        oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        oReq.withCredentials = true;
        oReq.send("app_username=aleksey&app_password=m123");
    }

    onClickRegister(evt) {
        evt.preventDefault();
        this.props.setScreen("register");
    }

    handleChange(evt) {
        this.setState({[evt.target.name]: evt.target.value});
    }
}