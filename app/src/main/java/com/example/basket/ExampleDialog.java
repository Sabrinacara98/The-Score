package com.example.basket;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {

    private String selection_person;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] person = getActivity().getResources().getStringArray(R.array.person);
        final String time;
        final String endtime;
        Bundle args = getArguments();
        time = args.getString("time");
        endtime = args.getString("endtime");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select person!")
                .setSingleChoiceItems(R.array.person, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selection_person = person[which];
                    }
                });

        builder.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Selected person: " +selection_person, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putString("time", time);
                args.putString("endtime", endtime);
                args.putString("person", selection_person);
                ActionDialog actionDialog =  new ActionDialog();
                actionDialog.setArguments(args);
                actionDialog.show(getFragmentManager(), "Action Dialog");

            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
