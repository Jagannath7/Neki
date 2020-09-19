import React, { Component } from "react";
import ReactDOM from 'react-dom';
import fire from './config/fire';
import LandingPage from '../LandingPage';
import props from 'react-dom';
import Structure from './Structure';

class Login extends Component{
  constructor(){
    super(props);
    this.state={
      user:{},
    }
  }
  componentDidMount(){
    this.authListener();
  }
   authListener() {
     fire.auth().onAuthStateChanged((user)=>{
       //console.log(user);
       if(user){
         this.setState({user});
       }else{
         this.setState({user:null});
       }
     });
   }
   render(){
    return(
      <div className="Login">
        {this.state.user ? (<LandingPage/>):(<Login/>)}
      </div>
    );
   }
   
}

export default Login;
