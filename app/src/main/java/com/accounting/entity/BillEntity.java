package com.accounting.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.accounting.daogen.DaoSession;
import com.accounting.daogen.CustomerEntityDao;
import org.greenrobot.greendao.annotation.NotNull;
import com.accounting.daogen.BillEntityDao;

/**
 * Created by Administrator on 2018/3/19.
 */
@Entity
public class BillEntity implements Parcelable{
    @Id(autoincrement = true)
    private Long BillId;
    @Property(nameInDb = "billCustomerNum")
    private long billCustomerNum;
    @Property(nameInDb = "customerName")
    private String customerName;
    @Property(nameInDb = "customerAddress")
    private String customerAddress;
    @Property(nameInDb = "address")
    private String address;
    @Property(nameInDb = "telephone")
    private String telephone;
    //发货人
    @Property(nameInDb = "consigner")
    private String consigner;
    @Property(nameInDb = "consignerTelephone")
    private String consignerTelephone;
    //收货人
    @Property(nameInDb = "consignee")
    private String consignee;
    @Property(nameInDb = "consigneeTelephone")
    private String consigneeTelephone;
    //结算方式，到付0。包邮1。
    @Property(nameInDb = "clearingForm")
    private int clearingForm;
    @Property(nameInDb = "amount")
    private int amount;
    @Property(nameInDb = "amountType")
    private String amountType;
    @Property(nameInDb = "weight")
    private double weight;
    @Property(nameInDb = "weightType")
    private String weightType;
    //运费
    @Property(nameInDb = "freight")
    private double freight;
    //已付
    @Property(nameInDb = "paid")
    private double paid;
    //垫付
    @Property(nameInDb = "advances")
    private double advances;
    //派送
    @Property(nameInDb = "sender")
    private String sender;
    //保额
    @Property(nameInDb = "coverage")
    private double coverage;
    //保费
    @Property(nameInDb = "premium")
    private double premium;
    //代收
    @Property(nameInDb = "collection")
    private String collection;
    @Property(nameInDb = "createTime")
    private long createTime;
    @Property(nameInDb = "beginAddress")
    private String beginAddress;
    @Property(nameInDb = "endAddress")
    private String endAddress;
    @Property(nameInDb = "paidState")
    private String paidState;
    @Property(nameInDb = "settlement")
    private String settlement;

    @ToOne(joinProperty = "billCustomerNum")
    private CustomerEntity customerEntity;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 370026791)
    private transient BillEntityDao myDao;

    @Generated(hash = 1355710333)
    public BillEntity(Long BillId, long billCustomerNum, String customerName,
            String customerAddress, String address, String telephone,
            String consigner, String consignerTelephone, String consignee,
            String consigneeTelephone, int clearingForm, int amount,
            String amountType, double weight, String weightType, double freight,
            double paid, double advances, String sender, double coverage,
            double premium, String collection, long createTime, String beginAddress,
            String endAddress, String paidState, String settlement) {
        this.BillId = BillId;
        this.billCustomerNum = billCustomerNum;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.address = address;
        this.telephone = telephone;
        this.consigner = consigner;
        this.consignerTelephone = consignerTelephone;
        this.consignee = consignee;
        this.consigneeTelephone = consigneeTelephone;
        this.clearingForm = clearingForm;
        this.amount = amount;
        this.amountType = amountType;
        this.weight = weight;
        this.weightType = weightType;
        this.freight = freight;
        this.paid = paid;
        this.advances = advances;
        this.sender = sender;
        this.coverage = coverage;
        this.premium = premium;
        this.collection = collection;
        this.createTime = createTime;
        this.beginAddress = beginAddress;
        this.endAddress = endAddress;
        this.paidState = paidState;
        this.settlement = settlement;
    }

    @Generated(hash = 635178700)
    public BillEntity() {
    }

    protected BillEntity(Parcel in) {
        if (in.readByte() == 0) {
            BillId = null;
        } else {
            BillId = in.readLong();
        }
        billCustomerNum = in.readLong();
        customerName = in.readString();
        customerAddress = in.readString();
        address = in.readString();
        telephone = in.readString();
        consigner = in.readString();
        consignerTelephone = in.readString();
        consignee = in.readString();
        consigneeTelephone = in.readString();
        clearingForm = in.readInt();
        amount = in.readInt();
        amountType = in.readString();
        weight = in.readDouble();
        weightType = in.readString();
        freight = in.readDouble();
        paid = in.readDouble();
        advances = in.readDouble();
        sender = in.readString();
        coverage = in.readDouble();
        premium = in.readDouble();
        collection = in.readString();
        createTime = in.readLong();
        beginAddress = in.readString();
        endAddress = in.readString();
        paidState = in.readString();
        settlement = in.readString();
        customerEntity = in.readParcelable(CustomerEntity.class.getClassLoader());
    }

    public static final Creator<BillEntity> CREATOR = new Creator<BillEntity>() {
        @Override
        public BillEntity createFromParcel(Parcel in) {
            return new BillEntity(in);
        }

        @Override
        public BillEntity[] newArray(int size) {
            return new BillEntity[size];
        }
    };

    public Long getBillId() {
        return this.BillId;
    }

    public void setBillId(Long BillId) {
        this.BillId = BillId;
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

    public String getConsigner() {
        return this.consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    public String getConsignerTelephone() {
        return this.consignerTelephone;
    }

    public void setConsignerTelephone(String consignerTelephone) {
        this.consignerTelephone = consignerTelephone;
    }

    public String getConsignee() {
        return this.consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeTelephone() {
        return this.consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }

    public int getClearingForm() {
        return this.clearingForm;
    }

    public void setClearingForm(int clearingForm) {
        this.clearingForm = clearingForm;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return this.amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeightType() {
        return this.weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    public double getFreight() {
        return this.freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getPaid() {
        return this.paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getAdvances() {
        return this.advances;
    }

    public void setAdvances(double advances) {
        this.advances = advances;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public double getCoverage() {
        return this.coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public double getPremium() {
        return this.premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getCollection() {
        return this.collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getBeginAddress() {
        return this.beginAddress;
    }

    public void setBeginAddress(String beginAddress) {
        this.beginAddress = beginAddress;
    }

    public String getEndAddress() {
        return this.endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getPaidState() {
        return this.paidState;
    }

    public void setPaidState(String paidState) {
        this.paidState = paidState;
    }

    public String getSettlement() {
        return this.settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    @Generated(hash = 1645141457)
    private transient Long customerEntity__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 37028145)
    public CustomerEntity getCustomerEntity() {
        long __key = this.billCustomerNum;
        if (customerEntity__resolvedKey == null
                || !customerEntity__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomerEntityDao targetDao = daoSession.getCustomerEntityDao();
            CustomerEntity customerEntityNew = targetDao.load(__key);
            synchronized (this) {
                customerEntity = customerEntityNew;
                customerEntity__resolvedKey = __key;
            }
        }
        return customerEntity;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1114527604)
    public void setCustomerEntity(@NotNull CustomerEntity customerEntity) {
        if (customerEntity == null) {
            throw new DaoException(
                    "To-one property 'billCustomerNum' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.customerEntity = customerEntity;
            billCustomerNum = customerEntity.getCdId();
            customerEntity__resolvedKey = billCustomerNum;
        }
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
    @Generated(hash = 237607475)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBillEntityDao() : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (BillId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(BillId);
        }
        dest.writeLong(billCustomerNum);
        dest.writeString(customerName);
        dest.writeString(customerAddress);
        dest.writeString(address);
        dest.writeString(telephone);
        dest.writeString(consigner);
        dest.writeString(consignerTelephone);
        dest.writeString(consignee);
        dest.writeString(consigneeTelephone);
        dest.writeInt(clearingForm);
        dest.writeInt(amount);
        dest.writeString(amountType);
        dest.writeDouble(weight);
        dest.writeString(weightType);
        dest.writeDouble(freight);
        dest.writeDouble(paid);
        dest.writeDouble(advances);
        dest.writeString(sender);
        dest.writeDouble(coverage);
        dest.writeDouble(premium);
        dest.writeString(collection);
        dest.writeLong(createTime);
        dest.writeString(beginAddress);
        dest.writeString(endAddress);
        dest.writeString(paidState);
        dest.writeString(settlement);
        dest.writeParcelable(customerEntity, flags);
    }
}
