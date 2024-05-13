import Button from 'react-bootstrap/Button';

import styles from './ClockInOutWidget.module.css'

const ClockInOutWidget = () => {

    return (
        <>
        <div>
            <h2 className={styles.test}> Clock In</h2>
                          <Button variant="primary" className={styles.button}>Primary</Button>{' '}

            </div>
        </>)
    }

export default ClockInOutWidget;