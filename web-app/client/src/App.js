import React from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import LandingPage from "./components/LandingPage";
import Login from "./components/Login";
import Register from "./components/Register";
import { Router, Route, Switch } from "react-router-dom";
import history from "./history";
import Dashboard from "./components/dashboard/Dashboard";
import NoMacth from "./components/404";
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
        <Route path="/dashboard/:id" exact component={Dashboard} />
        <Route component={NoMacth} />
      </Switch>
    </div>
  );
}

export default App;
