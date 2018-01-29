import React, {Component} from 'react';
import './App.css';

import Login from './FormLogin';
import Register from './FormRegister';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {screen: "login"};
        this.setScreen = this.setScreen.bind(this);
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
        let form = null;
        switch (scr) {
            case "login":
                form = <Login setScreen={this.setScreen}/>;
                break;

            case "register":
                form = <Register setScreen={this.setScreen}/>;
                break;

            default:
                form = <p>Error.</p>;
        }
        this.setState({screen: scr, form: form});
    }
}

export default App;
