package ca.bcit.assignment1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class PeopleAdapter extends ArrayAdapter<People>{

    Context _context;
    public PeopleAdapter(Context context, ArrayList<People> people) {
        super(context, 0, people);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Activity activity = (Activity) _context;
        People people = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.people_row_layout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.peopleName);
        TextView teamName = convertView.findViewById(R.id.peopleTeam);
        TextView age = convertView.findViewById(R.id.peopleAge);
        TextView nationality = convertView.findViewById(R.id.peopleNationality);
        TextView positionName = convertView.findViewById(R.id.peoplePosition);

        name.setText(people.getFullName());
        teamName.setText(people.getTeam().getName());
        age.setText("" + people.getCurrentAge());
        nationality.setText(people.getNationality());
        positionName.setText(people.getPrimaryPosition().getName());

        return convertView;
    }
}
