package com.example.basket;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ActionDialog extends AppCompatDialogFragment{
    private String selection_action;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String person;
        final String time;
        final String endtime;
        final String[] action = getActivity().getResources().getStringArray(R.array.action);
        Bundle args = getArguments();
        time = args.getString("time");
        person = args.getString("person");
        endtime = args.getString("endtime");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select action!")
                .setSingleChoiceItems(R.array.action, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selection_action = action[which];
                    }
                });

        builder.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Selected action: " +selection_action, Toast.LENGTH_SHORT).show();
                    Bundle args = new Bundle();
                    args.putString("time", time);
                    args.putString("endtime", endtime);
                    args.putString("person", person);
                    args.putString("action",selection_action);
                    ChangeDialog changeDialog =  new ChangeDialog();
                    changeDialog.setArguments(args);
                    changeDialog.show(getFragmentManager(), "Change Dialog");

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
