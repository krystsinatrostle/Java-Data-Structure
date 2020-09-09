/*
 * Created by Krystsina Trostle
 *
 * Created on June 3, 2020
 *
 * The objective of the program is to define the ContactNames class
 * that holds a collection of contact names and their corresponding 
 * phone numbers using the private data members, constructors, as well as   
 * all of the methods needed for the class              
 * 
 */
package Java2_Assign3;

public class ContactNames 
{
    // Define private variables to hold contact name and their phone number
    private String contactName;
    private String phoneNumber;

    // Constructor
    public ContactNames(String contactName,
            String phoneNumber)
    {
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
    }
    
    // Constructor
    public ContactNames(ContactNames contactNamesObject)
    {
        contactName = contactNamesObject.contactName;
        phoneNumber = contactNamesObject.phoneNumber;
    }

    //Method to set the contact name and throw exception if it's empty
    final public void setContactName(String contactName)
    {
        if (contactName.equals("")) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.contactName = contactName;
    }

    //Method to get the contact name
    public String getContactName()
    {
        return contactName;
    }

    // Method to set the phone number and throw exception if it's empty or 
    // not 7 or 10 numbers as well as to format it
    final public void setPhoneNumber(String phoneNumber)
    {
        if (phoneNumber.equals("")) {
            throw new IllegalArgumentException("Phone can't be empty");
        }
        phoneNumber = phoneNumber.replaceAll("[()-]", "");
        if (!phoneNumber.matches("^[0-9]{7}|[0-9]{10}$")) {
            throw new IllegalArgumentException("Phone Number must be 7 or 10 digits");
        }
        String phone = null;
        if (phoneNumber.length() == 7) {
            phone = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
        } else {
            phone = "(" + phoneNumber.substring(0,3) + ")" + phoneNumber.substring(3,6) 
                    + "-" + phoneNumber.substring(6);
        }
        this.phoneNumber = phone;
    }

    //Method to get the phone number
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    // Method to return the state of the current object
    @Override
    public String toString()
    {
        String contactNameContent = "Contact name: " + contactName +
                                   "\nPhone Number: "  + 
                                    phoneNumber;
        return contactNameContent;
    }
    
}// End of the class 
    
