package com.accounting.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.accounting.daogen.DaoSession;
import com.accounting.daogen.BillEntityDao;
import com.accounting.daogen.CustomerEntityDao;

/**
 * Created by Administrator on 2018/3/19.
 */
@Entity
public class CustomerEntity implements Parcelable{
    @Id(autoincrement = true)
    private Long cdId;
    @Property(nameInDb = "billCustomerNum")
    @Unique
    private long billCustomerNum;
    @Property(nameInDb = "customerName")
    private String customerName;
    @Property(nameInDb = "customerAddress")
    private String customerAddress;
    //商家区域
    @Property(nameInDb = "area")
    private String area;
    @Property(nameInDb = "telephone")
    private String telephone;
    //联系人
    @Property(nameInDb = "contacts")
    private String contacts;
    //结算方式，到付0。包邮1。
    @Property(nameInDb = "clearingForm")
    private int clearingForm;
    @Property(nameInDb = "createTime")
    private long createTime;

    @ToMany(referencedJoinProperty = "billCustomerNum")
    private List<BillEntity> billEntities;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1037488798)
    private transient CustomerEntityDao myDao;

    @Generated(hash = 889997190)
    public CustomerEntity(Long cdId, long billCustomerNum, String customerName,
            String customerAddress, String area, String telephone, String contacts,
            int clearingForm, long createTime) {
        this.cdId = cdId;
        this.billCustomerNum = billCustomerNum;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.area = area;
        this.telephone = telephone;
        this.contacts = contacts;
        this.clearingForm = clearingForm;
        this.createTime = createTime;
    }

    @Generated(hash = 457785240)
    public CustomerEntity() {
    }

    protected CustomerEntity(Parcel in) {
        if (in.readByte() == 0) {
            cdId = null;
        } else {
            cdId = in.readLong();
        }
        billCustomerNum = in.readLong();
        customerName = in.readString();
        customerAddress = in.readString();
        area = in.readString();
        telephone = in.readString();
        contacts = in.readString();
        clearingForm = in.readInt();
        createTime = in.readLong();
    }

    public static final Creator<CustomerEntity> CREATOR = new Creator<CustomerEntity>() {
        @Override
        public CustomerEntity createFromParcel(Parcel in) {
            return new CustomerEntity(in);
        }

        @Override
        public CustomerEntity[] newArray(int size) {
            return new CustomerEntity[size];
        }
    };

    public Long getCdId() {
        return this.cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public long getBillCustomerNum() {
        return this.billCustomerNum;
    }

    public void setBillCustomerNum(long billCustomerNum) {
        this.billCustomerNum = billCustomerNum;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContacts() {
        return this.contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public int getClearingForm() {
        return this.clearingForm;
    }

    public void setClearingForm(int clearingForm) {
        this.clearingForm = clearingForm;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1093201422)
    public List<BillEntity> getBillEntities() {
        if (billEntities == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BillEntityDao targetDao = daoSession.getBillEntityDao();
            List<BillEntity> billEntitiesNew = targetDao
                    ._queryCustomerEntity_BillEntities(cdId);
            synchronized (this) {
                if (billEntities == null) {
                    billEntities = billEntitiesNew;
                }
            }
        }
        return billEntities;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1626920035)
    public synchronized void resetBillEntities() {
        billEntities = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 164549197)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCustomerEntityDao() : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (cdId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(cdId);
        }
        dest.writeLong(billCustomerNum);
        dest.writeString(customerName);
        dest.writeString(customerAddress);
        dest.writeString(area);
        dest.writeString(telephone);
        dest.writeString(contacts);
        dest.writeInt(clearingForm);
        dest.writeLong(createTime);
    }
}
