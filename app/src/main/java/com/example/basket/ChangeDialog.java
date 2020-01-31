package com.example.basket;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ChangeDialog extends AppCompatDialogFragment {
    private String selection_team;
    private EditText player_in;
    private EditText player_out;
    private EditText player_number;
    private ChangeDialogListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ChangeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ChangeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] team = getActivity().getResources().getStringArray(R.array.team);
        final String action;
        final String person;
        final String time;
        final String endtime;
        Bundle args = getArguments();
        time = args.getString("time");
        endtime = args.getString("endtime");
        person = args.getString("person");
        action = args.getString("action");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (action.equals("Change")){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.changedialog, null);
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
                String playerIn = player_in.getText().toString();
                String playerOut = player_out.getText().toString();
                listener.applyTexts(time, endtime, person, action,selection_team, playerIn, playerOut);
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        player_in = view.findViewById(R.id.player_in_id);
        player_out = view.findViewById(R.id.player_out_id);
    }
        else{
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
                    String playerIn = player_number.getText().toString();
                    String playerOut = "null";
                    listener.applyTexts(time,endtime, person, action,selection_team, playerIn, playerOut);
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
        return builder.create();
}
    public interface ChangeDialogListener{
        void applyTexts(String time, String endtime, String person, String action,String selection_name, String playerIn, String playerOut);
    }
}

