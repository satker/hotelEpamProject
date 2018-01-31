import React, {Component} from 'react';
import {Table} from 'reactstrap';
import ItemOrder from './ItemOrder';

const URL = "http://localhost:8080/user/_id_/orders";

export default class ListOfRooms extends Component {
    constructor(props) {
        super(props);
        this.state = {list: null};
    }

    async componentDidMount() {
        let resp = await fetch(URL.replace("_id_", this.props.user().id));
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
                    <th>Capacity</th>
                    <th>Arrival date</th>
                    <th>Departure date</th>
                    <th>Service class</th>
                    <th>Description</th>
                    <th colSpan="2">Actions</th>
                </tr>
                </thead>
                <tbody>
                {this.state.list.map(order =>
                    <ItemOrder me={this.props.me()} user={this.props.user()} order={order}/>)}
                </tbody>
            </Table>
        );
    }
};