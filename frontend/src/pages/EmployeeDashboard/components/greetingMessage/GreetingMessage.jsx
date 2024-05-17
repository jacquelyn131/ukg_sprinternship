import styles from './GreetingMessage.module.css'
import { useState, useEffect } from "react";



const GreetingMessage = (props) => {


    const [greeting, setGreeting] = useState("Hello");

useEffect(() => {

      const currentTime = new Date().toLocaleTimeString('en-US', { hour12: false });
      const hour = parseInt(currentTime.split(":")[0]);

      console.log(hour);

   if (hour >= 0 && hour < 12) {
             setGreeting('Good Morning');
         } else if (hour >= 12 && hour < 18) {
             setGreeting('Good Afternoon');
         } else if (hour >= 18 && hour < 24) {
             setGreeting('Good Evening');
         }
    }, []);


return (
        <h1 className={styles.greeting}>{greeting}, {props.firstName}!</h1>
);
}

export default GreetingMessage;