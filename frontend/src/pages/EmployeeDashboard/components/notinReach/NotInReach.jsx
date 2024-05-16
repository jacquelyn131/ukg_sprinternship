import styles from './ClockInOutWidget.module.css'

const NotInReach = () => {

    return (
        <>

<div className={styles.location}>
    <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
              <h6 className ={styles.officeReach}>You are not within office reach</h6>
</div>

    );
}

export default NotInReach;
