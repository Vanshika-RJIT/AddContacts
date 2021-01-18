package com.example.minorproject.Model;

import java.util.ArrayList;
import java.util.List;

public class ContactSingletonClass {

    List<ContactInfo> contactInfoList;

    private ContactSingletonClass() {
     contactInfoList = new ArrayList<>();
    }

    private static final ContactSingletonClass contactSingletonClass = new ContactSingletonClass();

    public static  ContactSingletonClass getInstance() {
        return contactSingletonClass;
    }

    public void setContactInfoList(ContactInfo contactInfo) {
       contactInfoList.add(contactInfo);
    }

    public List<ContactInfo> getContactInfoList() {
        return contactInfoList;
    }

}
