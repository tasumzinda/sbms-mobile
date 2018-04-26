package org.totalit.sbms.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "branch", foreignKeys = @ForeignKey(entity = Client.class,
        parentColumns = "id",
        childColumns = "client_id",
        onDelete = CASCADE))
public class Branch {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "real_id")
    private int realId;

    @ColumnInfo(name = "sync_status")
    private int syncStatus;

    @ColumnInfo(name = "name")
    private String branch;

    @ColumnInfo(name = "address")
    private String address;

    public Branch(int id, int realId, int syncStatus, String branch, String address) {
        this.id = id;
        this.realId = realId;
        this.syncStatus = syncStatus;
        this.branch = branch;
        this.address = address;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
