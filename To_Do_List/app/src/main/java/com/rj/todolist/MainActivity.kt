package com.rj.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    // now we initialisation of components, and lateinit var is used becoz we don't want to initialise them now
    lateinit var item: EditText
    lateinit var add: Button
    lateinit var listView: ListView

    // now we need arraylist so, initialise the array list to hold the items
    var itemList= ArrayList<String>()

    // we need to access the write and read data from the helper class so we will create the object of them here
    // and when the app open we need to check whether there is an existing file or not if yes then take the data from that file and save it into the array list
    var fileHelper= FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // now we connect the components by their id's
        item= findViewById(R.id.editText)
        add= findViewById(R.id.button)
        listView= findViewById(R.id.list)

        // now to check whether there is an existing file or not if yes then take the data from that file and save it into the array list
        itemList= fileHelper.readData(this)

        // now we will send this data from arraylist to listview useing array adapter
        // this will take 4 parameters context, default layout of listview and default text and name of the arraylist
        var arrayAdapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)
        // now we will assign this adapter to the listview
        listView.adapter= arrayAdapter

        // now to add the data item to the listview when we click on the add button so we will create a click listener
        add.setOnClickListener {
            // now we will create a string container to save the data from edittextview
            // we use item here becoz we also have to add this itemm into the file also
            var itemName: String= item.text.toString()
            // we need to save that item to the array list first so that it can be used to save the data into the file
            itemList.add(itemName)
            // also we need to clear the editlist
            item.setText("")
            // now to write and save the data before the app is closed
            fileHelper.writeData(itemList,applicationContext)
            // also we need to notify the adapter to change/update these things
            arrayAdapter.notifyDataSetChanged()

        }

        // now we will write the code of the dialog box which will show when we click an item in the list view
        listView.setOnItemClickListener{adapterView,view,position,l ->
            // to buld the alert dialog
            var alert= AlertDialog.Builder(this)
            // to set the title of the dialog
            alert.setTitle("Delete")
            // dialog message
            alert.setMessage("Do you want to delete this item from the lsit?")
            // cancle the dialog is set to false so that if user click somewhere else then also the dialog won't disappear
            alert.setCancelable(false)
            // set the false or no or negative button's listener
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            // set the true button's listener
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                // here we give the poistion of the item in the arraylist which is to be removed
                itemList.removeAt(position)
                // now we notify the adapter so that the listview can be updated
                arrayAdapter.notifyDataSetChanged()
                // now we do update the this new list in the local file
                fileHelper.writeData(itemList,applicationContext)
            })
            // now we show the alert dialog
            alert.create().show()
        }
    }
}