import React, { useState, useRef } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";

import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";

import { makeStyles, useTheme } from "@material-ui/core/styles";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";

import Select from "@material-ui/core/Select";
import Chip from "@material-ui/core/Chip";

import Container from "@material-ui/core/Container";
import { db, auth, storage } from "../config/fire";
import firebase from '../config/fire';

/*Coparite funtion*/

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {"Copyright © "}
      <Link color="inherit" href="https://material-ui.com/">
        Neki
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

/*For styling */
const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
    maxWidth: 300,
  },
  chips: {
    display: "flex",
    flexWrap: "wrap",
  },
  chip: {
    margin: 2,
  },
  noLabel: {
    marginTop: theme.spacing(3),
  },
}));

/*Categary Dynimc styling */
const ITEM_HEIGHT = 60;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

/*Categaries */
const names = [
  "Food",
  "Clothing",
  "Electronics",
  "Education",
  "Essential",
  "Furniture",
  "Machinary",
  "Medical Equipment",
  "Sports",
  "Statinory",
  "Toys",
];

/*Catagaries dynamic styling */
function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

/*Main funtion */
export default function Register() {
  const classes = useStyles();
  const theme = useTheme();
  const uploadInputRef = useRef(null);
  /*
  intial setup
  */
  const initialFormData = {
    name: " ",
    tagline: " ",
    email: " ",
    phone: " ",
    street: " ",
    city: " ",
    state: " ",
    landmark: " ",
    iconimg: null,
    iconurl: " ",
    password: " ",
    pincode: " ",
  };

  /*For whole form exacpt category*/
  const [formData, updateFormData] = useState(initialFormData);
  /*For category option */
  const [personName, setPersonName] = React.useState([]);

  /*For handinling cat */
  const handleChangeCat = (event) => {
    setPersonName(event.target.value);
  };

  /*For handling whole funtion */
  const handleChange = (e) => {
    updateFormData({
      ...formData,

      // Trimming any whitespace
      [e.target.name]: e.target.value.trim(),
    });
  };

  const handleForm = (e) => {
    e.preventDefault();
    console.log(formData);
    /*
    personName is category result in form of array 
    */
    console.log(personName);

    /* 
    New Object created with cat as key and comma seprated choosed value
    */
    const c = {
      cat: personName.join(),
    };

    /* 
    returnedTarget is final form result
    */
    const returnedTarget = Object.assign(formData, c);
    console.log(returnedTarget);

    /*
    please change the following code be replacing formData by  returnedTarget 
    */
   
    var docid = formData.email;
    db.collection("ngos")
      .doc(docid)
      .set({
        categories: returnedTarget.cat,
        city: returnedTarget.city,
        landmark: returnedTarget.landmark,
        phoneNumber: returnedTarget.phone,
        state: returnedTarget.state,
        fullName: returnedTarget.name,
        tagline: returnedTarget.tagline,
        streetAddress: returnedTarget.street,
        iconUrl: returnedTarget.iconurl,
        pincode: returnedTarget.pincode,
      })
      .then(function (docRef) {
        console.log("Document written");
      })
      .catch(function (error) {
        console.error("Error adding document: ", error);
      });
      
      
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Register Your NGO
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                autoComplete="name"
                name="name"
                variant="outlined"
                required
                fullWidth
                id="Name"
                label="Name"
                autoFocus
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="tagline"
                label="tagline"
                name="tagline"
                autoComplete="tagline"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="phone"
                label="Phone"
                name="phone"
                autoComplete="phone"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="street"
                label="street"
                name="street"
                autoComplete="street"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="city"
                label="city"
                name="city"
                autoComplete="city"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="state"
                label="state"
                name="state"
                autoComplete="state"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="pincode"
                label="pincode"
                name="pincode"
                autoComplete="pincode"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="landmark"
                label="landmark"
                name="landmark"
                autoComplete="landmark"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12}>
              <FormControl fullWidth className={classes.formControl}>
                <InputLabel id="demo-mutiple-chip-label">Categary</InputLabel>
                <Select
                  fullWidth
                  variant="outlined"
                  labelId="demo-mutiple-chip-label"
                  id="demo-mutiple-chip"
                  multiple
                  value={personName}
                  onChange={handleChangeCat}
                  input={<Input id="select-multiple-chip" />}
                  renderValue={(selected) => (
                    <div className={classes.chips}>
                      {selected.map((value) => (
                        <Chip
                          key={value}
                          label={value}
                          className={classes.chip}
                        />
                      ))}
                    </div>
                  )}
                  MenuProps={MenuProps}
                >
                  {names.map((name) => (
                    <MenuItem
                      key={name}
                      value={name}
                      style={getStyles(name, personName, theme)}
                    >
                      {name}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>

            <Grid item xs={12}>
              <input
                ref={uploadInputRef}
                type="file"
                name="iconimg"
                accept="image/*"
                style={{ display: "none" }}
                onChange={handleChange}
              />
              <Button
                onClick={() =>
                  uploadInputRef.current && uploadInputRef.current.click()
                }
                variant="contained"
              >
                Upload Profile Picture
              </Button>
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleForm}
          >
            Sign Up
          </Button>
        </form>
      </div>
      <Box mt={5}>
        <Copyright />
      </Box>
    </Container>
  );
}
