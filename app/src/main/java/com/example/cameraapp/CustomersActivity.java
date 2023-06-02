package com.example.cameraapp;
/*
Name: Nsibande Lethukuthula
Student Number: 2018227377
 */

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

public class CustomersActivity extends AppCompatActivity {
    //Declaration of variables
    EditText txtName, txtAge;
    SwitchCompat swActive;
    AppCompatButton btnViewAll, btnAdd;
    ListView lvCustomerList;

    ArrayAdapter arrAdapterCustomer;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customers_main);

        // Assignment of variables to their layout counterparts
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        swActive = findViewById(R.id.swActive);
        btnAdd = findViewById(R.id.btnAdd);
        btnViewAll = findViewById(R.id.btnViewAll);
        lvCustomerList = findViewById(R.id.lvCustomerList);

        dbHelper = new DataBaseHelper(CustomersActivity.this);

        // Display customers on list view
        showCustomersOnListView(dbHelper);

        // when clicking the "Add" button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            CustomerModel customerModel;

            @Override
            public void onClick(View v) {
                try {
                    customerModel = new CustomerModel(-1, txtName.getText().toString(), Integer.parseInt(txtAge.getText().toString()), swActive.isChecked());
                    Toast.makeText(CustomersActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(CustomersActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    return;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(CustomersActivity.this);
                boolean bSuccess = dataBaseHelper.addOne(customerModel);
                Toast.makeText(CustomersActivity.this, "Success: " + bSuccess, Toast.LENGTH_SHORT).show();
                showCustomersOnListView(dataBaseHelper);
            }
        });

        // when clicking the "View All" button
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper _dbHelper = new DataBaseHelper(CustomersActivity.this);
                showCustomersOnListView(_dbHelper);
            }
        });

        // when clicking an item on list view
        lvCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dbHelper.deleteOne(clickedCustomer);
                showCustomersOnListView(dbHelper);
                Toast.makeText(CustomersActivity.this, "Deleted " + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // show customers method
    private void showCustomersOnListView(DataBaseHelper _dbHelper) {
        arrAdapterCustomer = new ArrayAdapter<CustomerModel>(CustomersActivity.this, android.R.layout.simple_list_item_1, _dbHelper.getEveryone());
        lvCustomerList.setAdapter(arrAdapterCustomer);
    }
}