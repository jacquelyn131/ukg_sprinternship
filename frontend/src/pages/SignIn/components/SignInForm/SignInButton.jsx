import React from "react";
import { Button } from "react-bootstrap";

const SignInButton = ({ children }) => {
  return (
    <>
      <style type="text/css">
        {`
          .btn-flat {
            background-color: #005151;
            color: white;
            border-radius: 20px;
            width: 100%;
          }

          .btn-xxl {
            font-size: 1rem;
          }
        `}
      </style>
        <Button className="btn-flat">
            {children}
        </Button>
    </>
  );
};

export default SignInButton;
