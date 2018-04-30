package org.totalit.sbms.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import org.totalit.sbms.dao.ClientDao;
import org.totalit.sbms.dao.ProcumentDocsDao;
import org.totalit.sbms.dao.UserDao;
import org.totalit.sbms.domain.Client;
import org.totalit.sbms.domain.ProcumentDocs;
import org.totalit.sbms.domain.User;

@Database(entities = {User.class, Client.class, ProcumentDocs.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userRepository();
    public abstract ClientDao clientRepository();
    public abstract ProcumentDocsDao procumentDocsDao();

    public static AppDatabase getInMemoryDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                            AppDatabase.class).build();
        }
        return INSTANCE;
    }
    public static AppDatabase getFileDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "sbmsdb").build();
        }
        return INSTANCE;
    }
    public  static void destroyInstance(){
        INSTANCE = null;
    }
}
