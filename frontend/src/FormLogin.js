import React, {Component} from 'react';
import './Form.css'

const URL = "http://localhost:8080/login";

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
            <form class="login" method="post" onSubmit={this.handleSubmit}>
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
        let resp = await fetch(URL, {method:"POST", body:this.state});
        alert(this.props.test);
        /** TODO **/
    }

    onClickRegister(evt) {
        evt.preventDefault();
        this.props.setScreen("register");
    }

    handleChange(evt) {
        this.setState({[evt.target.name]: evt.target.value});
    }
}