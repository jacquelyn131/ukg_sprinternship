package com.example.ukgtime;

public class User {
        private String email;
        private String password;

        // Constructor
        public User() {
        // Default constructor
    }

        // Getters and setters
        public String getEmail () {
        return email;
    }

        public void setEmail (String email){
        this.email = email;
    }

        public String getPassword () {
        return password;
    }

        public void setPassword (String password){
        this.password = password;
    }
}
