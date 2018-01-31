import React from "react";
import {Container, Table} from "reactstrap";

export default class AdminConfirmPage extends React.Component {
    render() {
        return (
            <Container>
                <h1>Confirmed requests of {this.props.user().firstName}</h1>
                <Table>
                    <thead>
                    <td>Number of room</td>
                    <td>Room capacity</td>
                    <td>Arrival date</td>
                    <td>Departure date</td>
                    <td>Cost one night</td>
                    </thead>
                    <tbody>

                    </tbody>
                </Table>
            </Container>
        );
    }
};
