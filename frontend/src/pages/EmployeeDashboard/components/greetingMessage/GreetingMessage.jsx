import styles from './GreetingMessage.module.css'
import { useState } from "react";


const GreetingMessage = (props) => {


    return <h1 className={styles.greeting}>Good morning, {props.firstName}!</h1>
    }

export default GreetingMessage;