package com.example.minorproject.ui.slideshow;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.minorproject.Model.ContactInfo;
import com.example.minorproject.Model.ContactSingletonClass;
import com.example.minorproject.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SlideshowFragment extends Fragment {

    View root;
    Button add_contact,pick_contact;
    EditText phone_no, name;
    TextInputLayout inputLayoutName, inputLayoutNumber;
    private static final int RESULT_PICK_CONTACT = 1;
    ContactSingletonClass contactSingletonClass;
    ContactInfo contactInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        phone_no = root.findViewById(R.id.phone_number);
        name = root.findViewById(R.id.person_name);
        inputLayoutName = root.findViewById(R.id.input_name);
        inputLayoutNumber = root.findViewById(R.id.input_number);
        add_contact = root.findViewById(R.id.btn_add_contact);
        pick_contact = root.findViewById(R.id.btn_pick_contact);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pick_contact.setOnClickListener(v -> {
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
        });
        add_contact.setOnClickListener(v -> {
            contactSingletonClass = ContactSingletonClass.getInstance();
            if (validateName() && validateNumber()) {
                String phoneNumber = phone_no.getText().toString();
                String Name = name.getText().toString();
                contactInfo = new ContactInfo(Name, phoneNumber);
                try {
                    contactSingletonClass.setContactInfoList(contactInfo);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "NullPointerException", Toast.LENGTH_LONG).show();
                }

            } else if (!validateNumber() || !validateName()) {
                return;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    Cursor cursor = null;
                    try {
                        String phoneNo = null;
                        String personName = null;
                        Uri uri = data.getData();
                        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                        cursor = getActivity().getContentResolver().query(uri,projection,
                                null, null, null);
                        cursor.moveToFirst();

                        int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        phoneNo = cursor.getString(numberColumnIndex);

                        int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        personName = cursor.getString(nameColumnIndex);

                        phone_no.setText(phoneNo);
                        name.setText(personName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    private boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.enter_name));
            root.requestFocus();
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNumber() {
        if (  phone_no.getText().toString().trim().isEmpty()) {
            inputLayoutNumber.setError(getString(R.string.enter_phone_number));
            root.requestFocus();
            return false;
        } else {
            inputLayoutNumber.setErrorEnabled(false);
        }

        return true;
    }
}