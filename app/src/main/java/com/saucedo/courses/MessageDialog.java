package com.saucedo.courses;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class MessageDialog extends DialogFragment {
    private OnClickDialogButton dialogButton;
    private Course course;
    private TextView title;
    private TextView category;
    private TextView date;
    private TextView duration;

    public MessageDialog(Course course){
        this.course=course;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view =requireActivity().getLayoutInflater().inflate(R.layout.message_dialog, null);
        //View view =getLayoutInflater().inflate(R.layout.message_dialog, null);
        this.title=view.findViewById(R.id.dialog_titulo);
        this.category=view.findViewById(R.id.dialog_categoria);
        this.date=view.findViewById(R.id.dialog_fecha);
        this.duration=view.findViewById(R.id.dialog_duracion);
        this.setDataView();

        this.dialogButton = (OnClickDialogButton) getActivity();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        dialogButton.positiveButton();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialogButton.negativeButton();
                    }
                }).create();
    }
    private  void setDataView(){
        this.title.setText(this.course.getTitle());
        this.category.setText(this.course.getCategoria());
        this.date.setText(this.course.getDate());
        this.duration.setText(this.course.getDuration());
    }
    public interface OnClickDialogButton {
        void positiveButton();
        void negativeButton();
    }
}