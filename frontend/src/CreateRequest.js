import React from "react";
import {Container, Form, Row, Col} from "reactstrap";

const URL = "http://localhost:8080/user/_id_/orders";

export default class CreateRequest extends React.Component {
    constructor(props) {
        super(props);
        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
        this.state = { roomType: {name: "ord", description: "small room"}};
    }

    onChange(evt) {
        this.setState({[evt.target.name]: evt.target.value})
    }

    async onSubmit(evt) {
        evt.preventDefault();
        let resp = await fetch(URL.replace("_id_", this.props.me().id), {
            method: "post",
            credentials: "include",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(this.state),
        });
        let text = await resp.text();
        console.log(text);
        this.props.goBack();
    }

    render() {
        return (
                <Form className="wide-form" onSubmit={this.onSubmit}>
                    <h2>Create request:</h2>
                    <Container>
                        <Row>
                            <Col>Capacity</Col>
                            <Col><input onChange={this.onChange} type="text" name="capacity"/></Col>
                        </Row>
                        <Row>
                            <Col>Arrival date</Col>
                            <Col><input onChange={this.onChange} type="date" name="arrivalDate"/></Col>
                        </Row>
                        <Row>
                            <Col>Departure date</Col>
                            <Col><input onChange={this.onChange} type="date" name="departureDate"/></Col>
                        </Row>
                        <Row>
                            <Col>Room type</Col>
                            <Col>
                                <select onChange={this.onChange} name="room_type">
                                    <option selected value="ord">ord</option>
                                    <option value="lux">lux</option>
                                    <option value="business">business</option>
                                </select>
                            </Col>
                        </Row>
                    </Container>
                    <input className="btn btn-success" type="submit" value="Send"/>
                </Form>
        );
    }
};
