package com.favas.myapplication.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Interf_Table30MN {

    @Insert
    public void Add_Record(TableBP_30MN tbl);

    @Insert
    public void Add_Full_Record(List<TableBP_30MN> list);







    @Query(" Delete from TableBP_30MN")
    public void Del_All_Record();


    @Query("Select Count(*) from TableBP_30MN")
    public  int getsize();








}
