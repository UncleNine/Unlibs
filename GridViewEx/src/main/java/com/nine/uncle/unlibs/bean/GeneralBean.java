package com.nine.uncle.unlibs.bean;

import android.os.Parcel;

/**
 * Created by Nine on 2016/11/12.
 */
public class GeneralBean extends FakeBean {
    public String id;
    public String name;
    public int    type;
    public String icon;

    public GeneralBean() {
        super();
    }

    public GeneralBean(String id, String name, int type, String icon, int fakeType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.fakeType = fakeType;
    }

    protected GeneralBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        type = in.readInt();
        icon = in.readString();
        fakeType = in.readInt();
    }

    public static final Creator<GeneralBean> CREATOR = new Creator<GeneralBean>() {
        @Override
        public GeneralBean createFromParcel(Parcel in) {
            return new GeneralBean(in);
        }

        @Override
        public GeneralBean[] newArray(int size) {
            return new GeneralBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(type);
        dest.writeString(icon);
        dest.writeInt(fakeType);
    }
}
