package com.accounting.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 12191 on 2018/1/29.
 */
@Entity
public class CompanyEntity implements Parcelable{
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "name")
    @Unique
    private String name;
    @Property(nameInDb = "address")
    private String address;
    @Property(nameInDb = "telephone")
    private String telephone;
    @Property(nameInDb = "complaintTel")
    private String complaintTel;
    @Property(nameInDb = "fax")
    private String fax;
    @Property(nameInDb = "email")
    private String email;
    @Property(nameInDb = "explainTxt")
    private String explainTxt;
    @Generated(hash = 171879863)
    public CompanyEntity(Long id, String name, String address, String telephone,
            String complaintTel, String fax, String email, String explainTxt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.complaintTel = complaintTel;
        this.fax = fax;
        this.email = email;
        this.explainTxt = explainTxt;
    }
    @Generated(hash = 1762635696)
    public CompanyEntity() {
    }

    protected CompanyEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        address = in.readString();
        telephone = in.readString();
        complaintTel = in.readString();
        fax = in.readString();
        email = in.readString();
        explainTxt = in.readString();
    }

    public static final Creator<CompanyEntity> CREATOR = new Creator<CompanyEntity>() {
        @Override
        public CompanyEntity createFromParcel(Parcel in) {
            return new CompanyEntity(in);
        }

        @Override
        public CompanyEntity[] newArray(int size) {
            return new CompanyEntity[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getComplaintTel() {
        return this.complaintTel;
    }
    public void setComplaintTel(String complaintTel) {
        this.complaintTel = complaintTel;
    }
    public String getFax() {
        return this.fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getExplainTxt() {
        return this.explainTxt;
    }
    public void setExplainTxt(String explainTxt) {
        this.explainTxt = explainTxt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(telephone);
        dest.writeString(complaintTel);
        dest.writeString(fax);
        dest.writeString(email);
        dest.writeString(explainTxt);
    }
}
