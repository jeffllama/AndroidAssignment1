package ca.bcit.assignment1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RosterAdapter extends ArrayAdapter<Person> {

    Context _context;
    public RosterAdapter(Context context, ArrayList<Person> roster) {
        super(context, 0, roster);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        Person person = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.roster_row_layout, parent, false);
        }

        TextView personName = convertView.findViewById(R.id.personName);

        personName.setText(person.getPersonDetails().getFullName());

        return convertView;
    }

}
