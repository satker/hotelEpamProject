import React, { Component } from 'react';

export default class ItemOrder extends Component {
    render() {
        let order = this.props.order;
        return (
            <tr>
                <td>{order.idDone ? "Ready" : "Pending"}</td>
                <td>{order.capacity}</td>
                <td>{order.arrivalDate}</td>
                <td>{order.departureDate}</td>
                <td>{order.roomType.name}</td>
                <td>{order.roomType.description}</td>
            </tr>
        );
    }
};