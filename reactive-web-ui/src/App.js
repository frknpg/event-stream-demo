import { useEffect, useState } from 'react';
import './App.css';
import logo from './logo.svg';

const App = () => {

  const [randomNumber, setRandomNumber] = useState(0);

  useEffect(() => {
    // const fetchRandomNumber = async () => {
    //   const response = await fetch('/api/random-number');
    //   const reader = response.body.getReader();
    //   const { value, done } = await reader.read();

    //   console.log(value);
    //   setRandomNumber(value);

    // };
    
    // fetchRandomNumber();

    const eventSource = new EventSource(`http://localhost:8080/api/random-number`);
    eventSource.onmessage = (e) => {
      console.log(e);
      setRandomNumber(e.data)
    };

    return () => eventSource.close();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <p>
          Random number is <code>{randomNumber}</code>
        </p>
      </header>
    </div>
  );
}

export default App;
