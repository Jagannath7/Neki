import React from "react";
import Header from "./Header";
import Section1 from "./Section1";
import Section2 from "./Section2";
import Footer from "./Footer";
import Navbar from "./Navbar";
import { Link } from "react-router-dom";
function LandingPage() {
  return (
    <>
      <Header />
      <Section1 />
      <Section2 />
      <Footer />
    </>
  );
}

export default LandingPage;
