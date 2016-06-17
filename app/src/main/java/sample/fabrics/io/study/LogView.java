package sample.fabrics.io.study;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseUser;


public class LogView extends AppCompatActivity {

    private CustomAdapter taskAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_view);
        listView = (ListView) findViewById(R.id.list_view);
        taskAdapter = new CustomAdapter(this);
        listView.setAdapter(taskAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //draws a menu on the android screen
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_plus_button:
                Intent i = new Intent(LogView.this, LogView.class);
                startActivity(i);
                return true;
            case R.id.menu_home_button:
                Intent j = new Intent(LogView.this, TimerActivity.class);
                startActivity(j);
                return true;
            case R.id.menu_sign_out:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                startActivity(new Intent(LogView.this, RegisterActivity.class));
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(LogView.this, TimerActivity.class);
        startActivity(i);
    }
}

