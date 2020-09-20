import React from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import Link from "@material-ui/core/Link";
import people from "../assests/people.png";
import "./Section2.css";
//import cx from "classnames";

const useStyles = makeStyles((theme) => ({
  mainFeaturedPost: {
    padding: "10px",
    backgroundColor: theme.palette.grey[800],
    color: theme.palette.common.white,
    marginBottom: theme.spacing(4),
    backgroundImage:
      "url(https://d19gb5k9ejx8w0.cloudfront.net/uploads/2016/11/29141529/Gifting_donations.jpg)",
    backgroundSize: "cover",
    backgroundRepeat: "no-repeat",
    backgroundPosition: "center",
  },
  overlay: {
    position: "absolute",
    top: 0,
    bottom: 0,
    right: 0,
    left: 0,
    // backgroundColor: "rgba(0,0,0,.3)",
  },
  mainFeaturedPostContent: {
    textAlign: "left",
    position: "relative",
    padding: theme.spacing(3),
    [theme.breakpoints.up("md")]: {
      padding: theme.spacing(6),
      paddingRight: 0,
    },
  },
}));

function Section2() {
  const classes = useStyles();
  const post = {
    title: "Let Us Join Hands",
    date: "date",
    description:
      "You have not lived today until you have done something for someone who can never repay you. Thats why we are asking NGOs to come and join hand with us and let us help you to connect you with the humanitarians of the world. Together for brighter future !!",
    imageTitle: "kuch tho tha",
    linkText: "Log in",
    image: { people },
  };

  return (
    <div className="head">
      <Paper
        className={classes.mainFeaturedPost}
        style={{ backgroundImage: `url(${post.image})` }}
      >
        {/* Increase the priority of the hero background image */}
        {
          <img
            style={{ display: "none" }}
            src={post.image}
            alt={post.imageText}
          />
        }
        <div className={classes.overlay} />
        <Grid container>
          <Grid item md={6}>
            <div className={classes.mainFeaturedPostContent}>
              <Typography
                component="h1"
                variant="h3"
                color="inherit"
                gutterBottom
              >
                {post.title}
              </Typography>
              <Typography variant="h5" color="inherit" paragraph>
                {post.description}
              </Typography>
              <div>
                <Link className="logs" href="/login">
                  {post.linkText}
                </Link>
                <Link className="regs" href="/register">
                  Register
                </Link>
              </div>
            </div>
          </Grid>
        </Grid>
      </Paper>
    </div>
  );
}

export default Section2;
