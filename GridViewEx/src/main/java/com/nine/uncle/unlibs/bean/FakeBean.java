package com.nine.uncle.unlibs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nine on 2016/11/12.
 */
public class FakeBean implements Parcelable {
    public int fakeType = 0;//0为真实,1为假,2为其他

    public FakeBean(int fakeType) {
        this.fakeType = fakeType;
    }

    public FakeBean() {
        super();
    }

    protected FakeBean(Parcel in) {
        fakeType = in.readInt();
    }

    public static final Creator<FakeBean> CREATOR = new Creator<FakeBean>() {
        @Override
        public FakeBean createFromParcel(Parcel in) {
            return new FakeBean(in);
        }

        @Override
        public FakeBean[] newArray(int size) {
            return new FakeBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(fakeType);
    }
}
