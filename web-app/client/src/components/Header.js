import React from "react";
import Box from "@material-ui/core/Box";
import "./Header.css";
import Grid from "@material-ui/core/Grid";

import { makeStyles } from "@material-ui/core/styles";
import people from "../assests/people.png";
import Link from "@material-ui/core/Link";
/*
This is code for Header used for showing title tagline and image
 > title : styling is to be done
 > tagline : styling is to be done
 > image : done
*/

const useStyles = makeStyles((theme) => ({
  title: {
    fontFamily: "Amiri",
    color: "#1b1b1f",
    paddingTop: "1%",
    paddingBottom: "1%",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    position: "relative",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",

    fontSize: "4rem",
    [theme.breakpoints.down("sm")]: {
      fontSize: "3em",
    },
  },
  hero: {
    height: "90vh",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    position: "relative",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    color: "#fff",
    fontSize: "4rem",
    [theme.breakpoints.down("sm")]: {
      height: 300,
      fontSize: "3em",
    },
  },

  tagLine: {
    fontFamily: "Inter",
    color: "#1b1b1f",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    position: "relative",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    fontSize: "1em",
    [theme.breakpoints.down("sm")]: {
      fontSize: "1em",
    },
  },

  heroImg: {
    height: "100%",
    width: "100%",
  },
}));

function Header() {
  const post = {
    linkText: "hello",
  };
  const classes = useStyles();
  return (
    <>
      <Box className={classes.title}>Neki</Box>
      <Box className={classes.tagLine}>
        Your old stuff, A new joy to some one
      </Box>
      <Box className={classes.hero}>
        <img
          className={classes.heroImg}
          src={people}
          alt="Sorry iamge is not Loading"
        />
      </Box>
    </>
  );
}

export default Header;
