import React, { Component } from 'react';
import './App.css';

import FormLogin from './FormLogin';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">EPAM Grand Hotel</h1>
        </header>
		<FormLogin />
      </div>
    );
  }
}

export default App;
