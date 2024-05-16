import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './App.module.css';
import { Stack } from "react-bootstrap";

import FeatureCard from "./pages/HomePage/components/FeatureCard/FeatureCard";

const App = () => {
    return (
        <div className={styles.homepageWrapper}>
            <div className={styles.contentWrapper}>
               <section className={styles.header}>
                  <div className={styles.headerContent}>
                       <h1>Welcome to UKG Time</h1>
                       <p>UKG Time is a time tracking application that allows you to easily clock in and out of work.</p>
                  </div>
                  <img src="./images/header-img.png" />
               </section>
               <section className={styles.features}>
                  <h2 id="features">Features</h2>
                  <p className={styles.featuresContent}>
                     UKG Time is your go-to for seamless time management and attendance tracking. Utilizing advanced location technology, employers can effortlessly monitor employee arrivals and departures, while features like location clock-in, hour/pay viewing, and time stats streamline the process for everyone.
                  </p>
                  <div className={styles.featureCardContainer}>
                     <FeatureCard featureName="Location Checker" featureDescription="Ensures employees are checking in from the office." imageUrl="./images/office location.png" />
                     <FeatureCard featureName="Clock In/Out Reporting" featureDescription="Displays Clock In time and saves timestamp history with Clock Out feature." imageUrl="./images/clock in out.png" />
                     <FeatureCard featureName="Manual Break Time" featureDescription="Allows employees the amount of time they've been on break according to their start." imageUrl="./images/break time recorded.png" />
                  </div>
               </section>
            </div>
        </div>
    );
};

export default App;
