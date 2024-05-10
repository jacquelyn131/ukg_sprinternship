package com.example.ukgtime;

public class ProfileImage {
    private long eId;
    private byte[] profileImage;

    @Override
    public String toString() {
        return String.format("ProfileImage[eId: %d, profileImage: '%s']",
                eId, profileImage==null ? "":profileImage.toString() );
    }

    public ProfileImage(long eId, byte[] profileImage) {
        this.eId = eId;
        this.profileImage = profileImage;
    }

    public long geteId() {
        return eId;
    }

    public void seteId(long eId) {
        this.eId = eId;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
