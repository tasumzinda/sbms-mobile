package org.totalit.sbms.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.totalit.sbms.domain.ProcumentDocs;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProcumentDocsDao {



    @Query("SELECT * FROM procurement_docs WHERE id IN (:userIds)")
    List<ProcumentDocs> findAllByIds(int[] userIds);

    @Query("SELECT * FROM procurement_docs WHERE client_id = :clientId")
    List<ProcumentDocs> findByClientId(int clientId);


    @Insert(onConflict = REPLACE)
    void insertProcumentDoc(ProcumentDocs ProcumentDocs);

    @Insert(onConflict = REPLACE)
    void insertAll(ProcumentDocs... ProcumentDocss);

}
