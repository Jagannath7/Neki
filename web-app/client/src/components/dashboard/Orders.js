import React, { useState, useEffect } from "react";
import Link from "@material-ui/core/Link";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import { db, auth, storage } from "../../config/fire";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Title from "./Title";
import { db, auth, storage } from "../../config/fire";
import firebase from '../../config/fire';

// Generate Order Data
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

function preventDefault(event) {
  event.preventDefault();
}

const useStyles = makeStyles((theme) => ({
  seeMore: {
    marginTop: theme.spacing(3),
  },
}));

export default function Orders(props) {
  /* 
  this props.id is email addreess
  */
  console.log(props.id);
  db.collection("transactions").where("toEmail", "==",props.id).where("status","==","ACCEPTED")
    .get()
    .then(function(querySnapshot) {
        querySnapshot.forEach(function(doc) {
            // doc.data() is never undefined for query doc snapshots
            console.log(doc.id, " => ", doc.data());
            let fulldata=doc.data();
            console.log(fulldata);
        });
    })
    .catch(function(error) {
        console.log("Error getting documents: ", error);
    });



  const classes = useStyles();
  const [state, setstate] = useState(rows);

  useEffect(() => {
    console.log(props.id);
    db.collection("transactions")
      .where("toEmail", "==", props.id)
      .where("status", "==", "ACCEPTED")
      .get()
      .then(function (querySnapshot) {
        let combinedData = [];
        // console.log(querySnapshot);
        querySnapshot.forEach(function (doc) {
          // console.log(doc.data());
          // doc.data() is never undefined for query doc snapshots
          // console.log(doc.id, ' => ', doc.data());
          combinedData.push(doc.data());

          // console.log(fulldata);
        });

        // console.log(combinedData);
        setstate(combinedData);
      })
      .catch(function (error) {
        console.log("Error getting documents: ", error);
      });
  }, []);

  console.log(state);
  return (
    <React.Fragment>
      <Title>New Orders</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Serial no</TableCell>
            <TableCell>Name</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {state.map((row, index) => (
            <TableRow key={index}>
              <TableCell>{index + 1}</TableCell>
              <TableCell>{row.fromName}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </React.Fragment>
  );
}
