import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

import { Button, Stack } from "react-bootstrap";

const App = () => {
  return (
      <div>Hello, world

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
