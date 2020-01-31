package com.example.basket;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FoulDialog extends AppCompatDialogFragment {
    private String selection_team;
    private EditText player_number;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] team = getActivity().getResources().getStringArray(R.array.team);
        final String action;
        final String person;
        final String time;
        Bundle args = getArguments();
        time = args.getString("time");
        person = args.getString("person");
        action = args.getString("action");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fouldialog,null);
        builder.setView(view);
        builder.setTitle("Select Team and Player(s)!")
                .setSingleChoiceItems(R.array.team, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selection_team = team[which];
                    }
                });

        builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Selected team: " +selection_team, Toast.LENGTH_SHORT).show();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        player_number = view.findViewById(R.id.player_number_id);
        return builder.create();
    }
}

