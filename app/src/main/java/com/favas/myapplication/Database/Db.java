package com.favas.myapplication.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {TableBP_30MN.class},version = 1)
public abstract class Db extends RoomDatabase {

    public abstract Interf_Table30MN getDAO_TbleBP_30MN();



}
