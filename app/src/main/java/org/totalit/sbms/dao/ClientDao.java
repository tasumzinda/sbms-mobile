package org.totalit.sbms.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import org.totalit.sbms.domain.Client;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
@Dao
public interface ClientDao {
    @Query("SELECT * FROM client")
    List<Client> getAll();

    @Query("SELECT * FROM client WHERE id IN (:userIds)")
    List<Client> findAllByIds(int[] userIds);

    @Query("Select * FROM client WHERE  name LIKE :name and active LIKE :active LIMIT 1 ")
    Client findByNameAndActive(String name, Boolean active);

    @Insert(onConflict = REPLACE)
    void insertUser(Client client);

    @Insert(onConflict = REPLACE)
    void insertAll(Client... clients);
}
