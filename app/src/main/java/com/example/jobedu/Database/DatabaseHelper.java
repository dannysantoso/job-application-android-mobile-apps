package com.example.jobedu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "jobedu";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Member(" +
                "MemberID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "Phone TEXT NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS Employer(" +
                "EmployerID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "Phone TEXT NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS Job(" +
                "JobID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "JobDesk TEXT NOT NULL," +
                "Company TEXT NOT NULL," +
                "Address TEXT NOT NULL," +
                "Salary INTEGER NOT NULL," +
                "Type TEXT NOT NULL," +
                "EmployerID INTEGER NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS DetailJob(" +
                "JobID INTEGER NOT NULL," +
                "MemberID INTEGER NOT NULL," +
                "Status TEXT NOT NULL," +
                "Date DATE NOT NULL" +
                ")");

        //----------------------------- DATABASE BUAT Article ----------------------

        db.execSQL("CREATE TABLE IF NOT EXISTS Article(" +
                "ArticleID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ArticleTittle TEXT NOT NULL," +
                "ArticleContent TEXT NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS Comment(" +
                "CommentID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Content TEXT NOT NULL," +
                "ArticleID INTEGER NOT NULL," +
                "MemberID INTEGER NOT NULL" +
                ")");

        //------------------------------------------------------------------------------------------------

        db.execSQL("INSERT INTO Member VALUES" +
                "(1, 'Danny', 'tes123', '5554')," +
                "(2, 'Varyan', 'tes123', '5554')");

        db.execSQL("INSERT INTO Employer VALUES" +
                "(1, 'employer', 'tes123', '5554')," +
                "(2, 'employer2', 'tes123', '5554')");

        db.execSQL("INSERT INTO Job VALUES" +
                "(1, 'Android Developer', 'GOOGLE', 'Singapore',20000000,'IT',1)," +
                "(2, 'Web Developer', 'GOJEK', 'Indonesia',15000000,'IT',2)," +
                "(3, 'UI/UX', 'Tokopedia', 'Indonesia',20000000,'DESIGN',3)");

        db.execSQL("INSERT INTO DetailJob VALUES" +
                "(1, 1, 'waiting', '24/12/019')," +
                "(2, 1, 'waiting', '24/12/2019')," +
                "(3, 2, 'interview', '24/12/2019')");

        db.execSQL("INSERT INTO Article VALUES" +
                "(1, 'Peluang Dapat Pekerjaan melalui Proses Walk in Interview', 'Setelah lulus kuliah, sudah lumrah rasanya jika seseorang ingin segera bekerja di suatu perusahaan---tak terkecuali saya. Namun, agar bisa bekerja di posisi atau kantor yang diinginkan tentu tidaklah mudah. Ada sesuatu yang harus ditaklukan terlebih dahulu, yakni wawancara kerja. \n" +
                "\n" +
                "')," +
                "(2, '5 Hal Yang Bisa Membuatmu Kena Blacklist Perusahaan', '1. Meski sangat menginginkan pekerja namun jangan sampai kamu membombardir perusahaan dengan lamaran kerjamu, 2. Nggak memenuhi panggilan interview juga jadi penyebab seseorang kena blacklist perusahaan, 3. Meneror perektrut juga bisa membuat namamu dicoret begitu saja lo!, 4. Alasan blacklist lainnya biasanya karena data yang ditulis pelamar tersebut bohong alias palsu, 5. Resign tanpa memberi kabar juga bisa membuatmu jadi pencari kerja yang namanya buruk\n')");

        db.execSQL("INSERT INTO Comment VALUES" +
                "(1, 'Terima kasih atas infonya !!!',1,1)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertJob(String jobdesk,String company, String address, String salary, String type, String employerid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("JobDesk",jobdesk);
        cv.put("Company",company);
        cv.put("Address",address);
        cv.put("Salary",salary);
        cv.put("Type",type);
        cv.put("EmployerID",employerid);
        long result = db.insert("Job", null, cv);
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean insertComment(String comment, String memberid, String articleid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Content",comment);
        cv.put("ArticleID",articleid);
        cv.put("MemberID",memberid);
        long result = db.insert("Comment", null, cv);
        if (result == -1){
            return false;
        }
        return true;
    }


    public boolean insertMember(String username, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", username);
        cv.put("Password", password);
        cv.put("Phone", phone);
        long result = db.insert("Member", null, cv);
        if (result == -1) {
            return false;
        }
        return true;
    }




        public Cursor getMemberID(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from Member where Username = ? and Password = ?", new String[]{username, password});

        return result;
    }

    public Cursor getEmployerID(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from Employer where Username = ? and Password = ?", new String[]{username, password});

        return result;
    }

    public boolean loginEmployer(String Username, String Password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Employer where Username = ? and Password = ?", new String[]{Username, Password});
        if(cursor.getCount()>0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean loginMember(String Username, String Password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Member where Username = ? and Password = ?", new String[]{Username, Password});
        if(cursor.getCount()>0) {
            return true;
        }else{
            return false;
        }
    }

    public Cursor getITJOB(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT Job.JobID, JobDesk, Company, Address, Salary, Type, EmployerID FROM Job WHERE Type = 'IT'", null);

        return result;
    }

    public Cursor getComment(String articleid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT MemberID, Content FROM Comment WHERE ArticleID = ?", new String[]{articleid});

        return result;
    }

    public Cursor getDESIGNJOB(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT Job.JobID, JobDesk, Company, Address, Salary, Type, EmployerID FROM Job WHERE Type = 'DESIGN'", null);

        return result;
    }

    public Cursor getArticle(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM Article", null);

        return result;
    }

    public Cursor getSEARCHJOB(String search){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT Job.JobID, JobDesk, Company, Address, Salary, Type, EmployerID FROM Job WHERE Company = ? ", new String[]{search});

        return result;
    }

    public Cursor getAppliedJob(String memberid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT DISTINCT Job.JobID, JobDesk, Company, Address, Salary, DetailJob.Status, DetailJob.Date FROM Job, DetailJob Where Job.JobID = DetailJob.JobID and DetailJob.MemberID = ? and (DetailJob.Status = 'waiting' OR DetailJob.Status = 'interview')", new String[]{memberid});

        return result;
    }

    public boolean applyJob(String jobid,String memberid, String status, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("JobID",jobid);
        cv.put("MemberID",memberid);
        cv.put("Status",status);
        cv.put("Date",date);
        long result = db.insert("DetailJob", null, cv);
        if (result == -1){
            return false;
        }
        return true;
    }

    public Integer deleteData(String jobid, String memberid, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("DetailJob","JobID = ? and MemberID = ? and Status = ?",new String[]{jobid,memberid,status});

    }

    public Cursor getPOSTEDJOB(String employedid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT Job.JobID, JobDesk, Company, Address, Salary, Type FROM Job WHERE EmployerID = ?", new String[]{employedid});

        return result;
    }

    public Cursor getAppliedMember(String jobid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT Member.MemberID, Username, Phone, Status FROM Member, DetailJob WHERE Member.MemberID = DetailJob.MemberID and JobID = ?", new String[]{jobid});

        return result;

    }

    public Integer rejectMember(String MemberID, String JobID, String Status){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("DetailJob","JobID = ? and MemberID = ? and Status = ?",new String[]{JobID,MemberID,Status});
    }

    public boolean updateStatus(String MemberID, String JobID, String StatusLama, String Date, String StatusBaru){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Status",StatusBaru);
        cv.put("Date",Date);
        db.update("DetailJob",cv,"JobID = ? and MemberID = ? and Status = ?", new String[]{MemberID,JobID,StatusLama});

        return true;
    }

    public boolean updateJobPost(String JobID, String JobDesk, String Company, String Address, String Salary, String Type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("JobDesk",JobDesk);
        cv.put("Company",Company);
        cv.put("Address",Address);
        cv.put("Salary",Salary);
        cv.put("Type",Type);
        db.update("Job",cv,"JobID = ? ", new String[]{JobID});

        return true;
    }

//    public Cursor getAllDataArticle(){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//
//
//
//    }

}

