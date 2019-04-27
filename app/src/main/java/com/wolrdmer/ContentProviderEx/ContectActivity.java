package com.wolrdmer.ContentProviderEx;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ContectActivity extends Activity {

    public Option opration;
    ContectResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = new ContectResolver(this);
    }

    public void display(View view) {
        opration = Option.DISPLAY;
        resolver.displayAll();
    }

    public void update(View view) {
        opration = Option.UPDATE;
        showDialog("Update");
    }

    public void delete(View view) {
        opration = Option.DELETE;
        showDialog("Delete");
    }

    public void add(View view) {
        opration = Option.ADD;
        showDialog("Add");
    }

    public void showDialog(String title) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view = layoutInflater.inflate(R.layout.layout_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(title);
        dialog.setCancelable(false);
        final EditText name = view.findViewById(R.id.etComments);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strname = name.getText().toString();
                if (null != strname && strname.length() > 0) {
                    if (opration == Option.ADD)
                        resolver.insert(strname);
                    if (opration == Option.DELETE)
                        resolver.delete(strname);
                    if (opration == Option.UPDATE)
                        resolver.update("yagnik", strname);
                }
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    enum Option {
        ADD, DELETE, UPDATE, DISPLAY
    }
}
