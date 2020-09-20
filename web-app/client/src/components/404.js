import React from "react";
import "./404.css";
import Link from "@material-ui/core/Link";
function NoMatch() {
  return (
    <div id="notfound">
      <div class="notfound">
        <div class="notfound-404">
          <h1>Oops!</h1>
        </div>
        <h2>404 - Page not found</h2>
        <p>
          The page you are looking for might have been removed had its name
          changed or is temporarily unavailable.
        </p>
        <Link href="/">Go To Homepage</Link>
      </div>
    </div>
  );
}

export default NoMatch;
