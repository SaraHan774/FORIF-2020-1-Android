package com.forif.study0518;

import android.os.Parcel;
import android.os.Parcelable;

// Person 클래스는 Parcelable 을 구현함으로써 인텐트로 전달할 수 있게 됩니다.
public class Person implements Parcelable {

    private String name;
    private String idNumber;

    public Person(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    protected Person(Parcel in) {
        name = in.readString();
        idNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(idNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }

}
