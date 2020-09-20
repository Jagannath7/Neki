import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardMedia from "@material-ui/core/CardMedia";
import CardContent from "@material-ui/core/CardContent";
import people from "../assests/people.png";
import food from "../assests/food.jpg";
import education from "../assests/education.jpg";
import donate from "../assests/donate.jpg";
/*
Ha ha muje pata h sub kuch hard coded hai vo jub api wala code karuge
tab ishe card akr k ek or sub component bana duga 

Ok :-)

Ps: 4:00 baje insaan or kya kya kare
*/
const useStyles = makeStyles((theme) => ({
  card: {
    maxWidth: "100%",
    border: "solid",
    borderRadius: "10px",
    boxShadow: "10px 10px 5px 0px rgba(138,58,138,0.4)",
  },
  media: {
    height: 240,
  },
  cardActions: {
    display: "flex",
    margin: "0 10px",
    justifyContent: "space-between",
  },
  author: {
    display: "flex",
  },
}));

function Section1() {
  const classes = useStyles();

  return (
    <Grid container spacing={3}>
      <Grid item xs={12} sm={6} md={4}>
        <Card className={classes.card}>
          <CardActionArea>
            <CardMedia
              className={classes.media}
              image={food}
              title="Contemplative Reptile"
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                Donate In Food !
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                These estimates show that while 27.8% of India’s population
                suffered from moderate or severe food insecurity in 2014-16, the
                proportion rose to 31.6% in 2017-19.
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      </Grid>

      <Grid item xs={12} sm={6} md={4}>
        <Card className={classes.card}>
          <CardActionArea>
            <CardMedia
              className={classes.media}
              image={education}
              title="Contemplative Reptile"
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                Donate in Education !
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                More than 27 percent of the country’s youth are excluded from
                education, employment, or training, while the overwhelming
                majority of working Indians are employed in the informal
                sectors.
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      </Grid>

      <Grid item xs={12} sm={6} md={4}>
        <Card className={classes.card}>
          <CardActionArea>
            <CardMedia
              className={classes.media}
              image={people}
              title="Contemplative Reptile"
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                Donate in Everyway possible !
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                No one has ever become poor by giving. So come forward and
                donate in whichever way you want to whether it is education,
                human resourse management , clothing , Shelter etc.
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      </Grid>
    </Grid>
  );
}

export default Section1;
