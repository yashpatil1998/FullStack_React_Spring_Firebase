import React from 'react';
import logo from './logo.svg';
import './App.css';
import GetDetails from './compenents/GetDetails';
import PostNewEmployee from './compenents/PostNewEmployee';

function App() {
  return (
    <div className="App">
      <h2>Full stack (React-Spring-Firebase)</h2>
      <GetDetails />
      <PostNewEmployee />
    </div>
  );
}

export default App;
