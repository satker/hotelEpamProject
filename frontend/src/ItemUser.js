import React, {Component} from "react";
import {Button} from "reactstrap";

const URL = "http://localhost:8080/user/_id_/orders";

export default class ItemUser extends Component {
    constructor(props) {
        super(props);

        this.toUserPage = this.toUserPage.bind(this);
        this.toUserRequests = this.toUserRequests.bind(this);
        this.toUserConfirm = this.toUserConfirm.bind(this);

        this.state = {
            ready: false,
            requests: "Loading...",
            confirmed: "Loading...",
        };
    }

    toUserPage() {
        this.props.setScreen("user_info", {user: this.props.user});
    }

    toUserRequests() {
        this.props.setScreen("list_of_orders", {user: this.props.user});
    }

    toUserConfirm() {
        this.props.setScreen("list_of_confirmed", {user: this.props.user});
    }

    async componentDidMount() {
        let resp = await fetch(URL.replace("_id_", this.props.user.id), {credentials: "include"});
        let text = await resp.text();
        let list = JSON.parse(text);

        let r = 0, c = 0;
        for (let order of list) {
            if (order.isDone) {
                c++;
            } else {
                r++;
            }
        }

        this.setState({requests: r, confirmed: c});
    }

    render() {
        let user = this.props.user;
        return (
            <tr>
                <td><Button className="btn-block" onClick={this.toUserPage}>{user.login}</Button></td>
                <td><Button className="btn-block" onClick={this.toUserRequests}>{this.state.requests}</Button></td>
                <td><Button className="btn-block" onClick={this.toUserConfirm}>{this.state.confirmed}</Button></td>
            </tr>
        );
    }
};
