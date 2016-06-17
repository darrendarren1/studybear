package sample.fabrics.io.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


/**
 * Created by earlybirdcamp on 6/15/16.
 */
class CustomAdapter extends ParseQueryAdapter<Form> {


    public CustomAdapter(Context context){
        super(context, new ParseQueryAdapter.QueryFactory<Form>(){
            public ParseQuery<Form> create(){
                ParseQuery query = new ParseQuery("Form");
                query.whereEqualTo("author", ParseUser.getCurrentUser());
                query.orderByDescending("time");
                return query;
            }
        });
    }


    @Override
    public View getItemView(Form form, View v, ViewGroup parent){

        super.getItemView(form, v, parent);
        LayoutInflater cellInflater = LayoutInflater.from(getContext());
        View customView = cellInflater.inflate(R.layout.custom_row, parent, false);

        TextView date = (TextView) customView.findViewById(R.id.firstLine);
        TextView time = (TextView) customView.findViewById(R.id.time);
        TextView productivity = (TextView) customView.findViewById(R.id.secondLine);
        TextView totalTime = (TextView) customView.findViewById(R.id.thirdLine);
        TextView notes = (TextView) customView.findViewById(R.id.notes);
        notes.setText(form.getNotes());
        date.setText(form.getLocation());
        totalTime.setText(form.getGroup());
        productivity.setText(form.getProductivity());
        time.setText(form.getTimer());
        return customView;

    }
}
