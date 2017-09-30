package com.popular_movies.domain.tv.seasons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public class Crew implements Parcelable{

    private String credit_id;
    private String department;
    private Integer gender;
    private Integer id;
    private String job;
    private String name;
    private String profile_path;

    protected Crew(Parcel in) {
        credit_id = in.readString();
        department = in.readString();
        job = in.readString();
        name = in.readString();
        profile_path = in.readString();
    }

    public static final Creator<Crew> CREATOR = new Creator<Crew>() {
        @Override
        public Crew createFromParcel(Parcel in) {
            return new Crew(in);
        }

        @Override
        public Crew[] newArray(int size) {
            return new Crew[size];
        }
    };

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(credit_id);
        dest.writeString(department);
        dest.writeString(job);
        dest.writeString(name);
        dest.writeString(profile_path);
    }
}
