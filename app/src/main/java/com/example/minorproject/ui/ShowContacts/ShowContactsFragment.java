package com.example.minorproject.ui.ShowContacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.minorproject.Adapter.ContactInfoAdapter;
import com.example.minorproject.Model.ContactSingletonClass;
import com.example.minorproject.R;

public class ShowContactsFragment extends Fragment {
   ContactSingletonClass contactSingletonClass;
   ContactInfoAdapter contactInfoAdapter = null;
   ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_contacts, container, false);
        listView = (ListView) root.findViewById(R.id.listContacts);
        contactSingletonClass = ContactSingletonClass.getInstance();
        contactInfoAdapter = new ContactInfoAdapter(getActivity(), R.layout.contact_info, contactSingletonClass.getContactInfoList());
        listView.setAdapter(contactInfoAdapter);
        return root;

    }
}