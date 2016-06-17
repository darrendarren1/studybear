package sample.fabrics.io.study;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


/**
 * Created by earlybirdcamp on 6/14/16.
 */
public class Pop extends Activity {


    EditText _location;
    RadioGroup _group;
    RadioGroup _productivity;
    RadioButton _groupChosen;
    RadioButton _productivityChosen;
    EditText _comments;
    TextView _timer;
    String _time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
//      submit button
        Button b_submit = (Button) findViewById(R.id.submitForm);
        Button b_exit = (Button) findViewById(R.id.exitButton);
        _location = (EditText) findViewById(R.id.location_input);
        _group = (RadioGroup) findViewById(R.id.ppl_group);
        _productivity = (RadioGroup) findViewById(R.id.pro_group);
        _comments = (EditText) findViewById(R.id.notes);
        _timer = (TextView) findViewById(R.id.timer);
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((_group.getCheckedRadioButtonId()) == -1 || _productivity.getCheckedRadioButtonId() == -1 || _time == null) {
                    Toast.makeText(getApplicationContext(), "Please fill out the entire form.", Toast.LENGTH_LONG).show();
                } else {

                    Form form = new Form();
                    form.setLocation(_location.getText().toString());
                    form.setGroup(_groupChosen.getText().toString());
                    form.setProductivity(_productivityChosen.getText().toString());
                    form.setNotes(_comments.getText().toString());
                    form.setTimer(_time);
                    form.setAuthor();
                    form.saveInBackground();
                    startActivity(new Intent(Pop.this, LogView.class));
                }
            }
        });
        b_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Pop.this, TimerActivity.class));
            }
        });
    }

    // radio buttons

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();


        // Check which radio button was clicked
        switch (view.getId()) {

            // single or group studying?
            case R.id.ppl_button1:
                if (checked)
                    // Single studying
                    _groupChosen = (RadioButton) findViewById(_group.getCheckedRadioButtonId());
                    break;
            case R.id.ppl_button2:
                if (checked)
                    // Group studying
                    _groupChosen = (RadioButton) findViewById(_group.getCheckedRadioButtonId());
                    break;

                // productivity levels?
            case R.id.pro_button1:
                if (checked)
                    // low
                    _productivityChosen = (RadioButton) findViewById(_productivity.getCheckedRadioButtonId());
                    break;
            case R.id.pro_button2:
                if (checked)
                    // medium
                    _productivityChosen = (RadioButton) findViewById(_productivity.getCheckedRadioButtonId());
                    break;
            case R.id.pro_button3:
                if (checked)
                    // high
                    _productivityChosen = (RadioButton) findViewById(_productivity.getCheckedRadioButtonId());
                    break;
        }

    }


    @Override
    protected void onResume(){
        super.onResume();
        _time = getIntent().getStringExtra("timerTime");
        _timer.setText(_time);
    }

    @Override
    public void onBackPressed() {

    }

}
