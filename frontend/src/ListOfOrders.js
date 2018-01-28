import React, {Component} from 'react';
import {Table} from 'reactstrap';
import ItemOrder from './ItemOrder';

const URL = "http://localhost:8080/user/2/orders";

export default class ListOfRooms extends Component {
    constructor(props) {
        super(props);
        this.state = {list: null};
    }

    async componentDidMount() {
        let resp = await fetch(URL);
        let data = await resp.text();
        this.setState({list: JSON.parse(data)});
    }

    render() {
        if (!this.state.list) {
            return (
                <p>Loading...</p>
            );
        }

        return (
            <Table hover>
                <thead>
                <tr>
                    <th>Status</th>
                    <th>Capacity</th>
                    <th>Arrival date</th>
                    <th>Departure date</th>
                    <th>Service class</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                {this.state.list.map(order =>
                    <ItemOrder order={order}/>)}
                </tbody>
            </Table>
        );
    }
};