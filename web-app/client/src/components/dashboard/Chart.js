import React from "react";
import { useTheme } from "@material-ui/core/styles";
import { ResponsiveContainer } from "recharts";
import Title from "./Title";
import people from "../../assests/people.png";

export default function Chart() {
  const theme = useTheme();

  return (
    <React.Fragment>
      <ResponsiveContainer>
        <img src={people} alt="logo" />
      </ResponsiveContainer>
    </React.Fragment>
  );
}
