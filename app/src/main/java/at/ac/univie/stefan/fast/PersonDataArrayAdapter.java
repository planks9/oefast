package at.ac.univie.stefan.fast;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.stefan.fast.Activities.ResultsActivity;
import at.ac.univie.stefan.fast.DataBase.PersonData;

import static at.ac.univie.stefan.fast.Activities.ResultsActivity.PRIMARYKEYFORPERSON;

/**
 * Created by Stefan on 25.05.2018.
 */

public class PersonDataArrayAdapter extends ArrayAdapter {

    private Context context;

    public PersonDataArrayAdapter(Context context, List<PersonData> personDataList) {
        super(context, 0, personDataList);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final PersonData personData = (PersonData) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(personData.getPersonname());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResultsActivity.class);
                intent.putExtra(PRIMARYKEYFORPERSON, personData.getPrimarykey());
                System.out.println("PrimKey: "+personData.getPrimarykey());
                context.startActivity(intent);

            }
        });


        return convertView;
    }
}
