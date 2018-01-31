import React from "react";
import {Button} from "reactstrap";

export default class ItemConfirmed extends React.Component {
    render() {
        let order = this.props.order;
        return (
            <tr>
                <td>{order.arrivalDate}</td>
                <td>{order.departureDate}</td>
                <td>{order.roomType}</td>
                <td>{order.roomNumber}</td>
                <td>{order.placeNumber}</td>
                <td>{order.nightCost}</td>
                {this.props.me.role === "ROLE_ADMIN" && <td>
                    <Button className="btn-danger">Delete</Button>
                </td>}
            </tr>
        );
    }
};
