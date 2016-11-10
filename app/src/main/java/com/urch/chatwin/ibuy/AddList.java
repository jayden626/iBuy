package com.urch.chatwin.ibuy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

public class AddList extends AppCompatActivity {
    TextView date;
    ArrayList<User> userList;
    DB_Handler db;
    Item editItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        date = (TextView) findViewById(R.id.chosen_date);

        db = new DB_Handler(this.getApplicationContext());

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null){
            int id = extras.getInt("id");
            editItem = db.getItem(id);
            setTexts(editItem);
        }

        userList = db.getAllUsers();
        Spinner users = (Spinner) findViewById(R.id.users);
        ArrayAdapter<User> spinnerArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_dropdown_item, userList);
        users.setAdapter(spinnerArrayAdapter);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void quantityUp(View v){
            EditText quanti = (EditText) findViewById(R.id.item_quantity);
            int number = Integer.parseInt(quanti.getText().toString());
            number++;
            quanti.setText(String.valueOf(number));
    }

    public void quantityDown(View v){
        EditText quanti = (EditText) findViewById(R.id.item_quantity);
        int number = Integer.parseInt(quanti.getText().toString());
        if(number > 1) {
            number--;
        }
        quanti.setText(String.valueOf(number));
    }

    public void setTexts(Item item){
        EditText nameField = (EditText) findViewById(R.id.item_name);
        EditText quantityField = (EditText) findViewById(R.id.item_quantity);
        EditText costField = (EditText) findViewById(R.id.item_cost);

        Spinner categoryField =(Spinner) findViewById(R.id.category);
        Spinner locationField =(Spinner) findViewById(R.id.location);
        Spinner userField = (Spinner) findViewById(R.id.users);
        User u = (User) userField.getSelectedItem();

        TextView dueField = (TextView) findViewById(R.id.chosen_date);
        ToggleButton common = (ToggleButton) findViewById(R.id.favourite);

        nameField.setText(item.getName());
        quantityField.setText(String.valueOf(item.getQuantity()));
        costField.setText(String.valueOf(item.getCost()));

        //set cat
        //set location
        //set user

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(item.getDue());
        dueField.setText(cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));

        common.setChecked(item.isCommon());
    }

    public void addItem(View v){
        EditText nameField = (EditText) findViewById(R.id.item_name);
        EditText quantityField = (EditText) findViewById(R.id.item_quantity);
        EditText costField = (EditText) findViewById(R.id.item_cost);

        Spinner categoryField =(Spinner) findViewById(R.id.category);
        Spinner locationField =(Spinner) findViewById(R.id.location);
        Spinner userField = (Spinner) findViewById(R.id.users);
        User u = (User) userField.getSelectedItem();

        TextView dueField = (TextView) findViewById(R.id.chosen_date);
        ToggleButton common = (ToggleButton) findViewById(R.id.favourite);

        String name = nameField.getText().toString().trim();
        String quantity = quantityField.getText().toString().trim();
        String cost = costField.getText().toString().trim();
        String category = categoryField.getSelectedItem().toString().trim();
        String location = locationField.getSelectedItem().toString().trim();
        String due = dueField.getText().toString().trim();
        String[] dates = due.split("/");

        Calendar currentDate = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();


        Toast toasts = Toast.makeText(this.getApplicationContext(), "name: "+name+" quan: " + quantity + " cost: "+cost+" category: "+category+" location: "+location+" due: "+due, Toast.LENGTH_SHORT);
        toasts.show();

        if(TextUtils.isEmpty(name)){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(TextUtils.isEmpty(quantity)){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Please enter a quantity", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(TextUtils.isEmpty(cost)){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Please enter a cost", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(TextUtils.isEmpty(category)){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Please enter a category", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(TextUtils.isEmpty(location)){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Please enter a location", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(TextUtils.isEmpty(due) || dates.length != 3){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Please pick a date", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            dueDate.set(Integer.parseInt(dates[2]), Integer.parseInt(dates[0]), Integer.parseInt(dates[1]));
            if(editItem != null) {
                Item item = new Item(editItem.getId(), name, category, location, Double.parseDouble(cost), Integer.parseInt(quantity), currentDate.getTimeInMillis(), dueDate.getTimeInMillis(), common.isChecked(), u.getId());
                db.updateItem(item);
                Toast toast = Toast.makeText(this.getApplicationContext(), "Updated Item", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Item item = new Item(-1, name, category, location, Double.parseDouble(cost), Integer.parseInt(quantity), currentDate.getTimeInMillis(), dueDate.getTimeInMillis(), common.isChecked(), u.getId());
                db.addItem(item);
                Toast toast = Toast.makeText(this.getApplicationContext(), "Added Item", Toast.LENGTH_SHORT);
                toast.show();
            }

            Intent intent = new Intent(this, List.class);
            startActivity(intent);
        }

    }
}
