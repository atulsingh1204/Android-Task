package com.example.newassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.newassignment.Adapter.EmployeeAdapter;
import com.example.newassignment.Api.ApiInterface;
import com.example.newassignment.Api.Myconfig;
import com.example.newassignment.LocalDatabase.DBHelper;
import com.example.newassignment.ModelList.EmployeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    ArrayList<EmployeList> employeLists = new ArrayList<EmployeList>();
    ArrayList<EmployeList> List = new ArrayList<EmployeList>();

    RecyclerView Rec_employee;
    DBHelper dbHelper;
    Boolean insert;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rec_employee=findViewById(R.id.Rec_employee);
        dbHelper = new DBHelper(MainActivity.this);

        progressDialog = new ProgressDialog(MainActivity.this);

        getEmployeeDetailList();

        ReadDataFromLocalDb();
    }

    private void ReadDataFromLocalDb() {


        Cursor cursor = dbHelper.readEmployeeData();
        while (cursor.moveToNext()){
            EmployeList obj = new EmployeList(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            List.add(obj);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
            Rec_employee.setLayoutManager(gridLayoutManager);
            EmployeeAdapter adapter = new EmployeeAdapter(MainActivity.this,List);
            Rec_employee.setAdapter(adapter);



        }
    }

    private void getEmployeeDetailList()
    {
//
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result =apiInterface.employeedetails();



        result.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

                try {
                    progressDialog.dismiss();
                    if (response.isSuccessful()){


                        String output = response.body().string();
                        Log.d("Response", "employeedetails" + output);
                        JSONObject jsonObject = new JSONObject(output);

                        if (jsonObject.getString("status").equals("success"))
                        {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject object = jsonArray.getJSONObject(i);
                                employeLists.add(new EmployeList(object));

                                insert = dbHelper.InsertEmployee(employeLists.get(i).getId(), employeLists.get(i).getEmployee_name(), employeLists.get(i).getEmployee_salary(), employeLists.get(i).getEmployee_age(), employeLists.get(i).getProfile_image());


                            }

                            if (insert == true){
                                Log.e("Response", "Data Updated");
                            }
                            else if (insert == false){

                                Log.e("Response", "Data Not Updated");
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
                        }

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Response", "" +e);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Response", "" +e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                progressDialog.dismiss();
                Log.e("Response", "" +t);

            }
        });
    }
}