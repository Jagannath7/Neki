import React from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import LandingPage from "./components/LandingPage";
import Login from "./components/Login";
import Register from "./components/Register";
import { Router, Route, Switch } from "react-router-dom";
import history from "./history";
import Dashboard from "./components/dashboard/Dashboard";
/*
working on routing
*/

function App() {
  return (
    <div>
      <Navbar text="login" />
      <Switch>
        <Route path="/" exact component={LandingPage} />
        <Route path="/login" exact component={Login} />
        <Route path="/register" exact component={Register} />
        <Route path="/dashboard" exact component={Dashboard} />
      </Switch>
    </div>
  );
}

export default App;
