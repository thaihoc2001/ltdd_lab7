package com.example.lab7_lttbdd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    private ListView peopleListView;
    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleListView = findViewById(R.id.peopleList);

        userData = new UserData(this);

        addActionForAddBtn();

        userData.addPeople(new User("Nguyen Thai Hoc"));
        userData.addPeople(new User("Nguyen Duy Hieu"));
        userData.addPeople(new User("Nguyen Van Lam"));
        userData.addPeople(new User("Nguyen Thi Oanh"));
        userData.addPeople(new User("Pham Phu Thu"));
        userData.addPeople(new User("Nguyen Trung Hieu"));

        showAllPeople();
    }

    public void addActionForAddBtn(){
        Button btnAdd = findViewById(R.id.btnAdd);
        EditText edtName = findViewById(R.id.edtAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();

                if (!name.equals("")){
                    userData.addPeople(new User(name));

                    showAllPeople();

                    edtName.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Name have to not null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addActionForRemoveBtn(){
        Button btnRemove = findViewById(R.id.btnRemove);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRow = peopleListView.getSelectedItemPosition();

                if (selectedRow != -1){
                    View selectedView = peopleListView.getSelectedView();

                    TextView txvId = selectedView.findViewById(R.id.peopleID);

                    int id = Integer.parseInt(txvId.getText().toString());

                    /**
                     *
                     */
                }
                else{
                    Toast.makeText(getApplicationContext(), "Name have to not null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showAllPeople(){
        List<User> list = userData.getAllPeoples();

        userAdapter = new UserAdapter(
                getApplicationContext(),
                R.layout.user_item,
                list
        );
        peopleListView.setAdapter(userAdapter);
    }
}