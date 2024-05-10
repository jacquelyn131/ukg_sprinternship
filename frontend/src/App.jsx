import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import { Button, Stack } from "react-bootstrap";

const App = () => {

  return (


      <div>Hello, world

        <div className="test">
          <h1 className="test">Test</h1>
        </div>

<Stack direction="horizontal" gap={2}>
  <Button as="a" variant="primary">
    Test Button
  </Button>
  <Button as="a" variant="success">
    Test Button 2
  </Button>
</Stack>;

      </div>
  );
};

export default App;
