package org.totalit.sbms.Database;


import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import org.totalit.sbms.dao.ClientDao;
import org.totalit.sbms.dao.ProcumentDocsDao;
import org.totalit.sbms.dao.UserDao;
import org.totalit.sbms.domain.Client;
import org.totalit.sbms.domain.ProcumentDocs;
import org.totalit.sbms.domain.User;

@Database(entities = {User.class, Client.class, ProcumentDocs.class}, version = 1)
public abstract class AbstractDb extends RoomDatabase {

    public abstract UserDao userRepository();
    public abstract ClientDao clientRepository();
    public abstract ProcumentDocsDao procumentDocsDao();
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
