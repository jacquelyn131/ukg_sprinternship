import styles from './FeatureCard.module.css';

const FeatureCard = (props) => {

    return (
        <>
<div className={styles.featureCard}>
            <img src="https://placehold.co/600x400.png" alt="Feature" />
            <h3>{props.featureName}</h3>
            <p>{props.featureDescription}</p>
    </div>
        </>
        )}

        export default FeatureCard;