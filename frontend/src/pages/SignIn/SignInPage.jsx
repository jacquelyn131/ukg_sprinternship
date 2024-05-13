import SignInForm from "./components/SignInForm/SignInForm";
import styles from "./SignInPage.module.css";

const SignInPage = () => {
  return (
    <div className={styles.signInWrapper}>
      <div className={styles.contentWrapper}>
        <h1>Sign In to UKG Time</h1>
        <SignInForm />
      </div>
    </div>
  );
};

export default SignInPage;
