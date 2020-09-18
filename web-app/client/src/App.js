import React from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Header from "./components/Header";
import Section1 from "./components/Section1";
import Section2 from "./components/Section2";
import Footer from "./components/Footer";
/*
Hi fellow coder aka Sidhi mam !!! if you are reading this, that implies that 
i am on bed, sleeping due to overdose of coffee. So please work on 

1. Navbar component 
2. ha ha muje pata jize hard coded h hai since data he nhai tha
   so aap dikkat maat lena vo m shuba change kar duga jada dher nhai lagegi usme !

I am writing this documentation because I am bored as F*** and I dont want code more !!! ;-) 

Thank you !!
your junior ..
naaam nhai likh rha ab m  :-))
*/

function App() {
  return (
    <div className="App">
      <Navbar />
      <Header />
      <Section1 />
      <Section2 />
      <Footer />
    </div>
  );
}

export default App;
