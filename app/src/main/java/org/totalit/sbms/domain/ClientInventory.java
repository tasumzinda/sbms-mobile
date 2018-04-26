package org.totalit.sbms.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "client_inventory", foreignKeys = @ForeignKey(entity = Client.class, parentColumns="id", childColumns="client_id"))
public class ClientInventory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "real_id")
    private int realId;

    @ColumnInfo(name = "sync_status")
    private int syncStatus;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "quantity")
    private String quantity;

    @ColumnInfo(name = "need_maintenance")
    private String needMaintenace;

    public ClientInventory(int id, int realId, int syncStatus, String category, String brand, String model, String quantity, String needMaintenace) {
        this.id = id;
        this.realId = realId;
        this.syncStatus = syncStatus;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
        this.needMaintenace = needMaintenace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRealId() {
        return realId;
    }

    public void setRealId(int realId) {
        this.realId = realId;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNeedMaintenace() {
        return needMaintenace;
    }

    public void setNeedMaintenace(String needMaintenace) {
        this.needMaintenace = needMaintenace;
    }
}
