import styles from './GreetingMessage.module.css'
import { useState } from "react";

const GreetingMessage = () => {

    return <h1 className={styles.greeting}>Good morning, Jesse!</h1>
    }

export default GreetingMessage;