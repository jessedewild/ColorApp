package com.jesse.colorapp;

import android.os.Parcel;
import android.os.Parcelable;

public class SavedColor implements Parcelable {
    private int[] color;

    public SavedColor(int[] HEXColor) {
        this.setColor(HEXColor);
    }

    public int[] getColor() {
        return color;
    }

    public int getFirstColorInt() {
        return color[0];
    }

    public int getSecondColorInt() {
        return color[1];
    }

    public int getThirdColorInt() {
        return color[2];
    }

    public void setColor(int[] HEXColor) {
        this.color = HEXColor;
    }

    @Override
    public String toString() {
        return color[0] + " " + color[1] + " " + color[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.color);
    }

    protected SavedColor(Parcel in) {
        this.color = in.createIntArray();
    }

    public static final Parcelable.Creator<SavedColor> CREATOR = new Parcelable.Creator<SavedColor>() {
        @Override
        public SavedColor createFromParcel(Parcel source) {
            return new SavedColor(source);
        }

        @Override
        public SavedColor[] newArray(int size) {
            return new SavedColor[size];
        }
    };
}
