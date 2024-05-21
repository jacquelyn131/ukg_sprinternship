import styles from './InReachIndicator.module.css';

const InReachIndicator = (props) => {
    return (
        <>
            { props.isWithinReach ? (
                <div className={styles.location} style={{ backgroundColor: "#E4FAD9" }}>
                    <img src="./././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                    <h6 className="officeReach">You are in office reach</h6>
                </div>
            ) : (
                <div className={styles.location} style={{ backgroundColor: "#EFE4BE" }}>
                    <img src="./././public/images/no-location-icon.svg" className={styles.locationIcon} alt="" />
                    <h6 className="officeReach">You are out of office reach</h6>
                </div>
            )}
        </>
    );
};

export default InReachIndicator;
