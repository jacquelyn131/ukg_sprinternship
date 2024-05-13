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
    <p className={styles.featuresContent}>We could put some general text about the app here. Like an overview of what it does/why it’s cool. Some feature ideas: location clock in, hour/pay viewing & time stats. If we have time: shift trading, shout outs. </p>

<div className={styles.featureCardContainer}>
    <FeatureCard featureName="Feature 1" featureDescription="Lorem ipsum dolor sit amet" />
    <FeatureCard featureName="Feature 2" featureDescription="Lorem ipsum dolor sit amet" />
    <FeatureCard featureName="Feature 3" featureDescription="Lorem ipsum dolor sit amet" />

    </div>
    </section>
            </div>
        </div>
    );
};

export default App;