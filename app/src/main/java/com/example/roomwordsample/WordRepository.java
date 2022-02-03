package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * A repository manages queries
 * allows you to use multiple backends
 * the repository implements the logic
 * for deciding whether to fetch data from a network
 * or use results cached in a local database
 * repository provides a clean API for data accesss to the rest of the application
 */
public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    /**
     * The DAO is passed into the repository constructor as opposed to the whole database.
     * This is because you only need access to the DAO, since it contains all the read/write methods
     * for the database
     */
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }


    /**
     * returns the livedata lsit of words from Room
     * Room executes all queries on a seperate thread
     * then observed LiveData will notify the observer on the main thread when the data is changed.
     * @return
     */
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    /**
     * we need not to run the insert on main thread
     * so, we use the ExecutorService
     * we created in the WordRoomDatabase to perform the insert on a background thread.
     * @param word
     */


    // You must call this on non-UI thread or your app will throw an exception.
    // Room ensures that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.insert(word);
        });
    }

}
