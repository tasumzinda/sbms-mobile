package org.totalit.sbms.domain;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "procurement_docs" ,foreignKeys = @ForeignKey(entity = Client.class, parentColumns = "id",childColumns = "client_id"))
public class ProcumentDocs {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int procid;

    @ColumnInfo(name = "real_id")
    private int realId;

    @ColumnInfo(name = "sync_status")
    private int syncStatus;

    @ColumnInfo(name = "application_letter")
    private String appLetter;

    @ColumnInfo(name = "company_profile")
    private String companyProfile;

    @ColumnInfo(name = "cert_of_incorporation")
    private String certOfIncor;

    @ColumnInfo(name = "mou")
    private String mou;

    @ColumnInfo(name = "cr_fourteen")
    private String cr;

    @ColumnInfo(name = "vat_reg_cert")
    private String vat;

    @ColumnInfo(name = "itf")
    private String itf;

    @ColumnInfo(name = "trade_license")
    private String trade_lic;

    @ColumnInfo(name = "traceable_ref")
    private String trace_ref;

    @ColumnInfo(name = "client_id")
    private String clientid;

    public ProcumentDocs(int procid, int realId, int syncStatus, String appLetter, String companyProfile, String certOfIncor, String mou, String cr, String vat, String itf, String trade_lic, String trace_ref, String clientid) {
        this.procid = procid;
        this.realId = realId;
        this.syncStatus = syncStatus;
        this.appLetter = appLetter;
        this.companyProfile = companyProfile;
        this.certOfIncor = certOfIncor;
        this.mou = mou;
        this.cr = cr;
        this.vat = vat;
        this.itf = itf;
        this.trade_lic = trade_lic;
        this.trace_ref = trace_ref;
        this.clientid = clientid;
    }

    public int getProcid() {
        return procid;
    }

    public void setProcid(int procid) {
        this.procid = procid;
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

    public String getAppLetter() {
        return appLetter;
    }

    public void setAppLetter(String appLetter) {
        this.appLetter = appLetter;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getCertOfIncor() {
        return certOfIncor;
    }

    public void setCertOfIncor(String certOfIncor) {
        this.certOfIncor = certOfIncor;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getItf() {
        return itf;
    }

    public void setItf(String itf) {
        this.itf = itf;
    }

    public String getTrade_lic() {
        return trade_lic;
    }

    public void setTrade_lic(String trade_lic) {
        this.trade_lic = trade_lic;
    }

    public String getTrace_ref() {
        return trace_ref;
    }

    public void setTrace_ref(String trace_ref) {
        this.trace_ref = trace_ref;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
}
