import React, {Component} from 'react';
import './App.css';

import Form from './ListOfUsers';

class App extends Component {
    render() {
        return (
            <div>
                <header className="App-header">
                    <h1 className="App-title">EPAM Grand Hotel</h1>
                </header>
                <Form/>
            </div>
        );
    }
}

export default App;
