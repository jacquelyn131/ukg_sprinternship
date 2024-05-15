import React, { useEffect, useState } from 'react';
import { Outlet, useNavigate } from "react-router-dom";

import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Button from 'react-bootstrap/Button';

import styles from './Layout.module.css';
import classNames from 'classnames';

import { useUser } from '../UserContext';

const Layout = () => {

    const { setLoginToken, setUserInfo, loginToken } = useUser();
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('login_token');
        setLoginToken(null);
        setUserInfo(null);
        navigate('/');
    }


  const logoPath =  
  './images/UKGtimelogo.svg';

  const [navbarHeight, setNavbarHeight] = useState(0);

  useEffect(() => {
    const navbar = document.getElementById('navbar');
    if (navbar) {
      setNavbarHeight(navbar.offsetHeight);
      document.body.style.paddingTop = `${navbar.offsetHeight}px`;
    }
  }, []);


  return (
   <>


<Navbar id="navbar" expand="lg"
 fixed="top"
                className={classNames(styles.navbar)}>
            <Navbar.Brand href="/"> 
                <img src={logoPath}
                     alt="Logo" 
                     id={styles.navbarLogo}/> 
              
            </Navbar.Brand> 

            <Navbar.Toggle  
                aria-controls="basic-navbar-nav" /> 
            <Navbar.Collapse  
                id="basic-navbar-nav"> 
                <Nav className={classNames("ms-auto", styles.navlinksContainer)}>
                <Nav.Link href="/" className={classNames(styles.navLink)}> 
                        Home
                    </Nav.Link>



                 
         { loginToken ? (
                       <>

                                                                    <Button onClick={handleLogout} className={classNames(styles.navbarButton)}>
                                                                        Logout
                                                                    </Button>
                       </>

                    ) : (

                        <>


                            <Nav.Link href="/#features" className={classNames(styles.navLink)}>
                                                                    Features
                                                                </Nav.Link>
                        <Button href="/signin" className={classNames(styles.navbarButton)}>
                            Sign In
                        </Button>

                        </>
                    )

                    }
           
                </Nav> 
            </Navbar.Collapse> 
        </Navbar> 

    <main>
    <Outlet />
      </main>
   </>
  );
}

export default Layout;