import React, {Component} from 'react';
import './App.css';

import Login from './FormLogin';
import Register from './FormRegister';
import ListOfUsers from './ListOfUsers';
import ListOfOrders from './ListOfOrders';

class App extends Component {
    constructor(props) {
        super(props);
        this.setScreen = this.setScreen.bind(this);
        this.state = {
            screen: "login",
            forms: {
                login: <Login setScreen={this.setScreen}/>,
                register: <Register setScreen={this.setScreen}/>,
                list_of_users: <ListOfUsers setScreen={this.setScreen}/>,
                list_of_orders: <ListOfOrders setScreen={this.setScreen}/>,
            },
        };
    }

    componentWillMount() {
        this.setScreen(this.state.screen);
    }

    render() {
        return (
            <div>
                <header className="App-header">
                    <h1 className="App-title">EPAM Grand Hotel</h1>
                </header>
                {this.state.form}
            </div>
        );
    }

    setScreen(scr) {
        this.setState({screen: scr, form: this.state.forms[scr]});
    }
}

export default App;
