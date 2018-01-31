import React, { Component } from 'react';
import {Button} from "reactstrap";

const URL_DELETE = "http://localhost:8080/user/_user_/order/_id_";

export default class ItemOrder extends Component {
    constructor(props) {
        super(props);
        this.deleteOrder = this.deleteOrder.bind(this);
    }

    async deleteOrder() {
        let url = URL_DELETE
            .replace("_user_", this.props.user.id)
            .replace("_id_", this.props.order.id);
        let resp = await fetch(url, {method: "delete"});
        console.log( await resp.text() );
    }

    confirmButton() {
        if( this.props.me.role === "ROLE_ADMIN") {
            return <td>
                <Button className="btn-success">Confirm</Button>
            </td>
        }
    }

    render() {
        let order = this.props.order;

        if(order.idDone) {
            return null;
        }

        let user = this.props.user;
        return (
            <tr>
                <td>{order.capacity}</td>
                <td>{order.arrivalDate}</td>
                <td>{order.departureDate}</td>
                <td>{order.roomType.name}</td>
                <td>{order.roomType.description}</td>
                <td><Button className="btn-danger" onClick={this.deleteOrder}>Delete</Button></td>
                {this.confirmButton()}
            </tr>
        );
    }
};