import styles from './GreetingMessage.module.css'
import { useState } from "react";

const GreetingMessage = () => {

    return (
            <div className={styles.greeting}>
                <h1>Goodmorning, Jesse!</h1>
            </div>
        );
    }

export default GreetingMessage;