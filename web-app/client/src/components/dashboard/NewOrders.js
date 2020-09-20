import React, { useState } from "react";
import Link from "@material-ui/core/Link";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Title from "./Title";
import { db, auth, storage } from "../../config/fire";
import firebase from '../../config/fire';
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";

function createData(id, date, name, From, nameofarticle, amount) {
  return { id, date, name, From, nameofarticle, amount };
}

const rows = [
  createData(
    0,
    "16 Mar, 2019",
    "Elvis Presley",
    "Tupelo, MS",
    "VISA ⠀•••• 3719",
    312.44
  ),
  createData(
    1,
    "16 Mar, 2019",
    "Paul McCartney",
    "London, UK",
    "VISA ⠀•••• 2574",
    866.99
  ),
  createData(
    2,
    "16 Mar, 2019",
    "Tom Scholz",
    "Boston, MA",
    "MC ⠀•••• 1253",
    100.81
  ),
  createData(
    3,
    "16 Mar, 2019",
    "Michael Jackson",
    "Gary, IN",
    "AMEX ⠀•••• 2000",
    654.39
  ),
  createData(
    4,
    "15 Mar, 2019",
    "Bruce Springsteen",
    "Long Branch, NJ",
    "VISA ⠀•••• 5919",
    212.79
  ),
];


// myArray = myArray.filter(function( obj ) {
//   return obj.id !== id;
// });

const useStyles = makeStyles((theme) => ({
  seeMore: {
    marginTop: theme.spacing(3),
  },
}));

export default function NewOrders() {
  const classes = useStyles();
  
  const [state, setstate] = useState(rows);
  const handleYesClick = (props) => {
    console.log(state, props);
    
    db.collection("transactions").where("toEmail", "==",props.id)
    .get()
    .then(function(querySnapshot) {
        querySnapshot.forEach(function(doc) {
            // doc.data() is never undefined for query doc snapshots
            console.log(doc.id, " => ", doc.data());
        });
    })
    .catch(function(error) {
        console.log("Error getting documents: ", error);
    });
  

    // myArray = myArray.filter(function (obj) {
    //   return obj.id !== id;
    // });
    setstate(
      state.filter(function (obj) {
        return obj.id !== props;
      })
    );
  };
  const handleNoClick = (props) => {
    console.log(state, props);

    // myArray = myArray.filter(function (obj) {
    //   return obj.id !== id;
    // });
    setstate(
      state.filter(function (obj) {
        return obj.id !== props;
      })
    );
   
  };
  return (
    <React.Fragment>
      <Title>New Orders</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Date</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>From</TableCell>
            <TableCell>Name of Article</TableCell>
            <TableCell>Amount</TableCell>
            <TableCell align="right">Option</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {state.map((row) => (
            <TableRow key={row.id}>
              <TableCell>{row.date}</TableCell>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.From}</TableCell>
              <TableCell>{row.nameofarticle}</TableCell>
              <TableCell>{row.amount}</TableCell>
              <TableCell align="right">
                <ButtonGroup
                  size="small"
                  aria-label="small secondary outlined button group"
                >
                  <Button onClick={() => handleYesClick(row.id)}>YES</Button>
                  <Button onClick={() => handleNoClick(row.id)}>NO</Button>
                </ButtonGroup>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      <div className={classes.seeMore}>
        <Link color="primary" href="/">
          Go home
        </Link>
      </div>
    </React.Fragment>
  );
}
