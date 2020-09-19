import React from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import LandingPage from "./components/LandingPage";
import Login from "./components/login/Login";
import { Router, Route, Switch } from "react-router-dom";
import history from "./history";
/*
working on routing
*/

function App() {
  return (
    <div>
      <Navbar />
      <Switch>
        <Route path="/" exact component={LandingPage} />
        <Route path="/login" exact component={Login} />
      </Switch>
    </div>
  );
}

export default App;
