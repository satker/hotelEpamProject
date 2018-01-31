import React from "react";
import {Form, Table, Container, Input} from "reactstrap";

const URL = "http://localhost:8080/user/_id_";

export default class EditProfile extends React.Component {
    constructor(props) {
        super(props);
        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
        this.state = {
            firstName: props.me().firstName,
            lastName: props.me().lastName,
        };
    }

    async onSubmit(evt) {
        evt.preventDefault();

        let body = {
            login: this.props.me().login,
        };
        for (let key of ["firstName", "lastName"]) {
            if (this.state[key]) {
                body[key] = this.state[key];
            }
        }

        let password = this.state.password;
        if (password === "") {
            password = null;
        }

        let confirm = this.state.confirmPassword;
        if (confirm === "") {
            confirm = null;
        }

        if (password) {
            if (password === confirm) {
                body.password = password;
            } else {
                /* TODO: error */
                console.log("Password " + password + " != " + confirm);
            }
        } else {
            body.password = this.props.me().password;
        }

        let resp = await fetch(URL.replace("_id_", this.props.me().id), {
            method: "put",
            credentials: "include",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(body),
        });

        if( resp.status === 200 ) {
            this.props.me().firstName = body.firstName;
            this.props.me().lastName = body.lastName;
            this.props.goBack();
        } else {
            alert("Failed to update profile");
        }
    }

    onChange(evt) {
        this.setState({[evt.target.name]: evt.target.value});
    }

    render() {
        let me = this.props.me();
        return (
            <Container>
                <Form className="wide-form" onSubmit={this.onSubmit}>
                    <p><b>Edit your profile</b></p>
                    <Table>
                        <tbody>
                        <tr>
                            <td>First name</td>
                            <td><input type="text" name="firstName" onChange={this.onChange}/></td>
                        </tr>
                        <tr>
                            <td>Last name</td>
                            <td><input type="text" name="lastName" onChange={this.onChange}/></td>
                        </tr>
                        <tr>
                            <td>New password</td>
                            <td><input type="password" name="password" onChange={this.onChange}/></td>
                        </tr>
                        <tr>
                            <td>Confirm password</td>
                            <td><input type="password" name="confirmPassword" onChange={this.onChange}/></td>
                        </tr>
                        </tbody>
                    </Table>
                    <Input className="btn btn-success form-control" type="submit" value="Save changes"/>
                </Form>
            </Container>
        );
    }
};
