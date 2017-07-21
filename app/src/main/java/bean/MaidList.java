
package bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MaidList implements Parcelable
{

    private String madeId;
    private String name;
    private String phoneNumber;
    private String workLocation;
    private String religion;
    private String cookingType;
    private String experience;
    private String photo;
    private String verification;
    private String workTime;
    private String cost;
    private String rating;
    private boolean isFav;
    private int position;
    public final static Creator<MaidList> CREATOR = new Creator<MaidList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MaidList createFromParcel(Parcel in) {
            MaidList instance = new MaidList();
            instance.madeId = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
            instance.workLocation = ((String) in.readValue((String.class.getClassLoader())));
            instance.religion = ((String) in.readValue((String.class.getClassLoader())));
            instance.cookingType = ((String) in.readValue((String.class.getClassLoader())));
            instance.experience = ((String) in.readValue((String.class.getClassLoader())));
            instance.photo = ((String) in.readValue((String.class.getClassLoader())));
            instance.verification = ((String) in.readValue((String.class.getClassLoader())));
            instance.workTime = ((String) in.readValue((String.class.getClassLoader())));
            instance.cost = ((String) in.readValue((String.class.getClassLoader())));
            instance.rating = ((String) in.readValue((String.class.getClassLoader())));
            instance.isFav = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            return instance;
        }

        public MaidList[] newArray(int size) {
            return (new MaidList[size]);
        }

    }
    ;

    public String getMadeId() {
        return madeId;
    }

    public void setMadeId(String madeId) {
        this.madeId = madeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCookingType() {
        return cookingType;
    }

    public void setCookingType(String cookingType) {
        this.cookingType = cookingType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(madeId);
        dest.writeValue(name);
        dest.writeValue(phoneNumber);
        dest.writeValue(workLocation);
        dest.writeValue(religion);
        dest.writeValue(cookingType);
        dest.writeValue(experience);
        dest.writeValue(photo);
        dest.writeValue(verification);
        dest.writeValue(workTime);
        dest.writeValue(cost);
        dest.writeValue(rating);
        dest.writeValue(isFav);
    }

    public int describeContents() {
        return  0;
    }

}
