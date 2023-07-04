package sg.edu.rp.c346.id22024713.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lvTasks;
    EditText etTask, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.button);
        btnGetTasks = findViewById(R.id.button2);
        tvResults = findViewById(R.id.textView);
        lvTasks = findViewById(R.id.listView);
        etTask = findViewById(R.id.editTextText);
        etDate = findViewById(R.id.editTextText2);

        btnInsert.setOnClickListener(v -> {
            String etTaskInput = etTask.getText().toString();
            String etDateInput = etDate.getText().toString();

            DBHelper db = new DBHelper(MainActivity.this);
            db.insertTask(etTaskInput, etDateInput);
        });

        btnGetTasks.setOnClickListener(v -> {
            DBHelper db = new DBHelper(MainActivity.this);
            ArrayList<String> data = db.getTaskContent();
            ArrayList<Task> data2 = db.getTasks();

            db.close();
            String txt = "";
            for (int i = 0; i < data.size(); i++) {
                Log.d("Database Content", i + ". " + data.get(i));
                txt += i + ". " + data.get(i) + "\n";
            }

            ArrayAdapter aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data2);
            lvTasks.setAdapter(aaTasks);

            tvResults.setText(txt);

        });
    }
}