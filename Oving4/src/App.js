import React from "react";
import "./App.css";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { createMuiTheme } from "@material-ui/core/styles";
import { ThemeProvider } from "@material-ui/styles";
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';


const theme = createMuiTheme({
  palette: {
    primary: {
      main: "#ff3d42"
    },
    error: {
      main: "#fff",
      color: "white"
    }
  }
});

const useStyles = makeStyles({
  background: {
    backgroundColor: "#303030"
  },
  text: {
    color: "white"
  }
});

function App() {
  const classes = useStyles();
  const [checked, setChecked] = React.useState(false);

  const toggleChecked = () => {
    setChecked(prev => !prev);
  };

  return (
    <div className="App">
      <header className="App-header">
        <ThemeProvider theme={theme}>
          <TextField
            error
            input={{ style: { color: "#fff" } }}
            InputLabelProps={{
              style: { color: "white" }
            }}
            labelProps={{
              cclassName: classes.text
            }}
            InputProps={{
              className: classes.text
            }}
            id="standard-basic"
            label="Number"
          />
           <FormControlLabel
        control={<Switch checked={checked} onChange={toggleChecked} />}
        label="Pluss"
      />
        </ThemeProvider>
      </header>
    </div>
  );
}

export default App;
