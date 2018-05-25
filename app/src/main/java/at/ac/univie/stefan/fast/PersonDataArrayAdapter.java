package at.ac.univie.stefan.fast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.stefan.fast.DataBase.PersonData;

/**
 * Created by Stefan on 25.05.2018.
 */

public class PersonDataArrayAdapter extends ArrayAdapter {


    public PersonDataArrayAdapter(Context context, List<PersonData> personDataList) {
        super(context, 0, personDataList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PersonData personData = (PersonData) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(personData.getPersonname());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Klickadiklackklack");
            }
        });


        return convertView;
    }
}
