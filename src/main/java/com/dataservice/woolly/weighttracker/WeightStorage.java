package com.dataservice.woolly.weighttracker;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * Created by Ivan on 4/23/2017.
 */

public class WeightStorage {
    private static final String SAVE_FILE = "WeightData.dat";

    Boolean SaveData(LinkedList<WeightTracker.DateWeight> dataList, Context context)
    {
        Log.v("WEIGHTSTORAGE","ARE WE EVEN HERE?");
        try {
            Log.v("WEIGHTSTORAGE","Save data INside");
            FileOutputStream fos = context.openFileOutput(SAVE_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for (WeightTracker.DateWeight data : dataList) {
                os.writeObject(dataList);
            }
            os.close();
            fos.close();
            Log.v("WEIGHTSTORAGE","Save data Attempted");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    LinkedList<WeightTracker.DateWeight> LoadData(Context context){
        LinkedList<WeightTracker.DateWeight> dataList = new LinkedList<>();
        try {
            FileInputStream fis = context.openFileInput(SAVE_FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            dataList = (LinkedList<WeightTracker.DateWeight>) is.readObject();
            is.close();
            fis.close();
            Log.v("WEIGHTSTORAGE","Load data Attempted");
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
