import React, {Component} from 'react';
import './App.css';

import Login from './FormLogin';
import Register from './FormRegister';
import ListOfOrders from './ListOfOrders';
import MainUserPage from './MainUserPage';
import MainAdminPage from './MainAdminPage';
import UserInfoPage from './UserInfoPage';
import CreateRequest from './CreateRequest';
import ListOfConfirmed from './ListOfConfirmed';

class App extends Component {
    constructor(props) {
        super(props);

        this.setScreen = this.setScreen.bind(this);
        this.getCurrentUser = this.getCurrentUser.bind(this);
        this.getTargetUser = this.getTargetUser.bind(this);

        this.state = {
            me: null,
            user: null,
            screen: "login",
            forms: {
                login: <Login setScreen={this.setScreen}/>,
                register: <Register setScreen={this.setScreen}/>,
                list_of_orders: <ListOfOrders setScreen={this.setScreen} me={this.getCurrentUser} user={this.getTargetUser}/>,
                list_of_confirmed: <ListOfConfirmed me={this.getCurrentUser} user={this.getTargetUser}/>,
                user_home: <MainUserPage setScreen={this.setScreen} me={this.getCurrentUser}/>,
                admin_home: <MainAdminPage setScreen={this.setScreen} me={this.getCurrentUser}/>,
                user_info: <UserInfoPage setScreen={this.setScreen} me={this.getCurrentUser} user={this.getTargetUser}/>,
                create_request: <CreateRequest me={this.getCurrentUser}/>,
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

    setScreen(scr, opt) {
        let nextState = {screen: scr, form: this.state.forms[scr]};
        if (opt) {
            for (let k in opt) {
                nextState[k] = opt[k];
            }
        }
        this.setState(nextState);
    }

    getCurrentUser() {
        return this.state.me;
    }

    getTargetUser() {
        return this.state.user;
    }
}

export default App;
