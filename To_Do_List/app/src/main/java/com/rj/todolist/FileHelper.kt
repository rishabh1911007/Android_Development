package com.rj.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    // If i add items directly from arraylist to listview, that can cause a problem becoz if we close the app then the data will be removed so so need to save the list of items in a file which will be saved in device internal memory
    // so we need this class to write the saving code of data here so that the data can be saved in file
    // we can write this code in mainActivity kt but to show code easily we need this class

    // first we will give a name to the file
    val FILENAME= "listinfo.dat"

    // now we will write a fun which will write the data in the file, it will take to parameter
    // here item and context is object of arraylist and context class respectfully
    fun writeData(item: ArrayList<String>, context: Context){

        // first we will create a object of fileOutputStream class which will write the data in file
        // here fos is the object of that class
        // method openfileoutput will create and open the file to write
        // filename is the name of the file and mode will be private so that other app can't access that but this app can use it anywhere
        var fos: FileOutputStream= context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        // this and above class work together to write the data and oas is the object of this class
        var oas= ObjectOutputStream(fos)
        // now to write the item in the file
        // so when we call the writeData fun from mainActivity then it will get the item parameter to save the data
        // thus we can send the item to this fun which we want to add in the list and in last the new item will be saved in the local saved file
        oas.writeObject(item)
        // now we need to close this local file
        oas.close()

    }

    // now we need a fun to read the data from that file
    // this fun will return an arraylist of string data type
    fun readData(context: Context): ArrayList<String>{
        // first we need a new arraylist to read the data and save that data into this arraylist and then transfer it to the list view
        var itemList: ArrayList<String>

        // we need to write the try and catch fun and its operations so that first the necessary operations could take place like to save the data into the file and it's code will be in try block but what if there is no file present so to prevent the program to crash we need to create an empty arraylist to save the data and it's code will be in catch block and an empty listview will be dislayed at that time
        try{
            // now we will create an object of fileInputStream to use this class so that we can read the data from the file and give it the name of the file
            var fis: FileInputStream= context.openFileInput(FILENAME)
            // this and above class work together to read the data
            var ois= ObjectInputStream(fis)
            // now we transfer the data from the file to the arrayList, so we use readObject method to read and transfer but it will read the data in object form so we use as Arrayliststring to convert the data into string format
            itemList= ois.readObject() as ArrayList<String>
        }catch (e: FileNotFoundException){
            // in catch parameter we will write the error name which is show when the file is not found

            // create a empty arraylist
            itemList= ArrayList()
        }

        // since this fun will return a arraylist so
        return itemList

    }

    // now we can use these two fun in mainActi file
    // we need to access the write and read data from the helper class so we will create the object of them in main kt file
}