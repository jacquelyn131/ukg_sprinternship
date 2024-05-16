import styles from './GreetingMessage.module.css'
import { useState, useEffect } from "react";



const GreetingMessage = (props) => {


    const [greeting, setGreeting] = useState("Hello");

useEffect(() => {

      const currentTime = new Date().toLocaleTimeString();
 console.log(currentTime)
 if (currentTime > "12:00:00 AM" && currentTime < "11:59:59 AM") {
            setGreeting("Good Morning");
        } else if (currentTime > "12:00:00 PM" && currentTime < "5:59:59 PM") {
            setGreeting("Good Afternoon");
        } else if (currentTime > "6:00:00 PM" && currentTime < "11:59:59 PM") {
            setGreeting("Good Evening");
        }  else {
            setGreeting("Hello");
        }
    }, []);


return (
    <div className={styles.greetingMessage}>
        <h1 className={styles.greeting}>{greeting}, {props.firstName}!</h1>
    </div>
);
}

export default GreetingMessage;