import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import "./Navbar.css";

/*
So you are here !  well thank you !!
this is the code for navigation 
work to do :
 > Add Register button in the right side
 > Change background color
 > Add logo on left most side ( Jo apko kudh dunda hai )
*/

function Navbar() {
  return (
    <AppBar className="appBar" position="static">
      <Toolbar>
        <Typography variant="h6">Neki</Typography>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;
