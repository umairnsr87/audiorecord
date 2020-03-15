package com.pucho.audiorecord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pucho.audiorecord.MyTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private static final String url="jdbc:mysql://104.155.146.57:3306/test";
    private static final String user="umairnsr87";
    private static final String password="@@Codechef9@@";
    private TextView username,email;
    private Button button;

    Connection con=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        button=findViewById(R.id.showdata);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {

                Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_LONG)
                        .show();

                List<String> ls=new ArrayList<String>();

                getData gd=new getData();
                ls=gd.getdata();
                Log.v("error in connection tag", ls.get(0));
                username.setText(ls.get(0));
                email.setText(ls.get(1));


//                Connection con=MyTask.getCon();
//                Log.e("connection tag","connection sucessful"+con);
//
//                getData gd=new getData();
//                gd.setData(username.getText().toString().trim(),email.getText().toString().trim());
//


//                // TODO Auto-generated method stub
//                ConnectMySql connectMySql = new ConnectMySql();
//                connectMySql.execute("");
//                //new MyTask().execute();

            }
        });
    }

//    private Class MyTask extends AsyncTask<void,void,void>{
//
//    }


    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_SHORT)
                    .show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from registration");
                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next()) {
                    result += rs.getString(1).toString() + "\n";
                }
                res = result;
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            username.setText(result);
        }
    }
}


