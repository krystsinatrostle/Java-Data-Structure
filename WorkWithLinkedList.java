/*
 * Created by Krystsina Trostle
 *
 * Created on June 3, 2020
 *
 * The objective of the program is to define the WorkWithLinkedList class
 * that accepts the data from the user's screen, outputs it and 
 * interfaces with the ContactNames class. WorkWithQueue class allows
 * a user to add, delete, serach, sort, and count contacts. It also
 * displays the collection before and after each operation.
 * 
 */
package Java2_Assign3;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WorkWithLinkedList {
    private LinkedList<ContactNames> linkedListContacts; 
    private Stage stage = new Stage();
    GridPane contactPane = new GridPane();

    public WorkWithLinkedList()
    {
        linkedListContacts = new LinkedList<>();
        GridPane gridPane = new GridPane();
        buildComponents(gridPane);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setHeight(700);
        stage.setWidth(600);
        stage.setTitle("Data Structure - LinkedList Contact Names and Phone Numbers");
        contactPane.setVgap(10);
        contactPane.setHgap(10);
        stage.show();
    }

    // Removes node to get a new count or new search contact information
    public void removeGridPaneChild(GridPane pane, int col, int row) {
        ObservableList<Node> children = pane.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node n = children.get(i);
            if (GridPane.getColumnIndex(n) == col && 
                    GridPane.getRowIndex(n) == row) {
                children.remove(n);
                i--;
            }
        }
    }
    
    // Displays the collection after each operation in the phone directory
    private void rebuildContactPane() {
        contactPane.getChildren().clear();
        for (int i = 0; i < linkedListContacts.size(); i++) {
            ContactNames contact = linkedListContacts.get(i);
            contactPane.add(new Label(contact.getContactName()), 0, i);
            contactPane.add(new Label(contact.getPhoneNumber()), 1, i);
        }
    }
    
    // Highlights the searched contact and its corresponding phone number
    private void highlightContact(ContactNames contact) {
        String name = contact.getContactName();
        String phoneNumber = contact.getPhoneNumber();
        List<Node> children = contactPane.getChildren();
        for (Node child : children) {
            Label label = (Label)child;
            String text = label.getText();
            if (text.equals(name) || text.equals(phoneNumber)) {
                label.setStyle("-fx-background-color: yellow;");
            }
        }
    }
    
    private void buildComponents(GridPane gridPane)
    {
        // Define the label for the main menu GUI
        Label labelForLinkedList = new Label("Create and Work with a LinkedList "); 
        labelForLinkedList.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        // Add the label to the GridPane
        gridPane.add(labelForLinkedList, 1, 0, 2, 1);

        // Add the label and textfield for the contact name
        Label labelAddContactName = new Label("Add a Contact Name ");
        gridPane.add(labelAddContactName, 1, 2);
        TextField textAddContactName = new TextField();
        gridPane.add(textAddContactName, 2 ,2, 3, 1);
        
        // Add the label and textfield for the phone number
        Label labelAddPhone = new Label("Add a Phone Number ");
        gridPane.add(labelAddPhone, 1, 3);
        TextField textAddPhone = new TextField();
        gridPane.add(textAddPhone, 2, 3, 3, 1);

        // Create a button to enter data
        Button buttonToAddToLinkedList = new Button("Add a Contact Name and "
                + "Phone Number");
        // Add the button to the GridPane
        gridPane.add(buttonToAddToLinkedList, 2, 4, 3, 1);

        // Event handler for the add button to add contact name and phone number
        // and throw exception if the fields are empty         
        buttonToAddToLinkedList.setOnAction(event ->
        {
            ContactNames contactNameObject = null;
            try {
                contactNameObject = new ContactNames
                                          (textAddContactName.getText(),
                                           textAddPhone.getText());
            } catch (IllegalArgumentException e) {
                Stage dialog = new Stage();
                Label message = new Label(e.getMessage());
                Scene dialogScene = new Scene(message);
                message.setStyle("-fx-padding: 20px;");
                dialog.setScene(dialogScene);
                dialog.setTitle("Error");
                dialog.show();
                return;
            }
            linkedListContacts.add(contactNameObject);
            textAddContactName.clear();
            textAddPhone.clear();
            rebuildContactPane();
        });
        
        //Add color to the calculate button before and after the mouse 
        //hovers over it
        final String IDLE_BUTTON_STYLE = "-fx-background-color: PALEGREEN;";
        final String HOVERED_BUTTON_STYLE = "-fx-outer-border: PALEGREEN;";
        buttonToAddToLinkedList.setStyle(IDLE_BUTTON_STYLE);
        buttonToAddToLinkedList.setOnMouseEntered(e -> 
                buttonToAddToLinkedList.setStyle(HOVERED_BUTTON_STYLE));
        buttonToAddToLinkedList.setOnMouseExited(e -> 
                buttonToAddToLinkedList.setStyle(IDLE_BUTTON_STYLE));

        // Create button to sort a contact name and corresponding phone number
        // ignoring the case contact name starts with
        Button buttonToSort = new Button("Sort contacts");
        // Add the button to the GridPane
        gridPane.add(buttonToSort, 2, 5);
        buttonToSort.setOnAction(event ->
        {
            Comparator<ContactNames> comp = new Comparator<ContactNames>() {
                @Override
                public int compare(ContactNames c1, ContactNames c2) {
                    String n1 = c1.getContactName().toLowerCase();
                    String n2 = c2.getContactName().toLowerCase();
                    return n1.compareTo(n2);
                }
            };
            Collections.sort(linkedListContacts, comp);
        rebuildContactPane();
        });
        
        // Create button to count a contact name and corresponding phone number
        Button buttonToCount = new Button("Total contacts");
        // Add the button to the GridPane
        gridPane.add(buttonToCount, 3, 5);
        // Create an Event Handler for the button to count contact names
        buttonToCount.setOnAction(event ->
        {
            Label displayCount = new Label("" + linkedListContacts.size());
            displayCount.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
            removeGridPaneChild(gridPane, 4, 5);
            gridPane.add(displayCount, 4, 5);
        });

        // Create the label and textfield to search for a contact
        Label labelSearchContactName = new Label("Enter the Contact Name to "
                + "Search for");
        TextField textSearchContactName = new TextField();
        
        // Add the label and textfield for deletion to the GridPane
        gridPane.add(labelSearchContactName, 1, 6);
        gridPane.add(textSearchContactName, 2, 6, 3, 1);
        
        // Add the label for the search message 
        Label labelSearchMessage = new Label();
        labelSearchMessage.setVisible(true);
        gridPane.add(labelSearchMessage, 2, 8, 3, 1);
        
        Button buttonToSearch = new Button("Search for a contact");
        // Add the button to the GridPane
        gridPane.add(buttonToSearch, 2, 7, 3, 1);
        // Create an Event Handler for the button to serach a contact name
        // When found it will highlight the contact name and corresponding 
        // phone number and will display the toString from ContactNames class
        buttonToSearch.setOnAction(event ->
        {
            rebuildContactPane();
            Boolean foundSwitch = false;
            String holdContactName = textSearchContactName.getText();
            for (ContactNames contact : linkedListContacts) {
                if (contact.getContactName().equals(holdContactName)) {
                    highlightContact(contact);
                    Label displaySearch = new Label(contact.toString());  
                    removeGridPaneChild(gridPane, 2, 13);
                    gridPane.add(displaySearch, 2, 13, 2, 1);
                    displaySearch.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
                    foundSwitch = true;
                    }
            }
            if (foundSwitch.equals(true))
            {  
                labelSearchMessage.setText("Contact information was successfully "
                        + "found");
            }
            else
            {
                labelSearchMessage.setText("Contact information does not exist");
            }
            labelSearchMessage.setVisible(true);
            textSearchContactName.clear();
        });
        
        // Create the label and textfield to delete a contact name
        Label labelDeleteContactName = new Label("Enter the Contact Name to "
                + "Delete");
        TextField textDeleteContactName = new TextField();
        
        // Add the label and textfield for deletion to the GridPane
        gridPane.add(labelDeleteContactName, 1, 9);
        gridPane.add(textDeleteContactName, 2, 9, 3, 1);
        
        // Add the label for the delete message 
        Label labelDeleteMessage = new Label();
        labelDeleteMessage.setVisible(false);
        gridPane.add(labelDeleteMessage, 2, 11, 3, 1);
        
        // Create a button to remove a contact name and phone number
        Button buttonToRemove = new Button("Remove a Contact");
        // Add the button to the GridPane
        gridPane.add(buttonToRemove, 2, 10, 3, 1);
        // Create an Event Handler for the button to remove a contact name
        buttonToRemove.setOnAction(event ->
        {
            Boolean removeSwitch = false;
            for (int i = 0; i < linkedListContacts.size(); i++)
            {
                String holdContactName = textDeleteContactName.getText();
                if (linkedListContacts.get(i).getContactName().equals(holdContactName))
                {
                    linkedListContacts.remove(linkedListContacts.get(i));
                    removeSwitch = true;
                }
             
            }
            if (removeSwitch.equals(true))
            {  
                labelDeleteMessage.setText("Contact information was successfully "
                        + "deleted");
                removeGridPaneChild(gridPane, 2, 13);
            }
            else
            {
                labelDeleteMessage.setText("Contact information does not exist");
                removeGridPaneChild(gridPane, 2, 13);
            }
            labelDeleteMessage.setVisible(true);
            rebuildContactPane();
            textDeleteContactName.clear();
        });
        
        //Add color to the delete button before and after the mouse 
        //hovers over it
        buttonToRemove.setStyle("-fx-background-color: TOMATO;");
        final String IDLE_BUTTONR_STYLE = "-fx-background-color: TOMATO;";
        final String HOVERED_BUTTONR_STYLE = "-fx-outer-border: TOMATO;";
        buttonToRemove.setOnMouseEntered(e -> 
                buttonToRemove.setStyle(HOVERED_BUTTONR_STYLE));
        buttonToRemove.setStyle(IDLE_BUTTONR_STYLE);
        buttonToRemove.setOnMouseExited(e -> 
                buttonToRemove.setStyle("-fx-background-color: TOMATO;"));
          
        
        // Create a button to exit the WorkWithLinkedList program
        Button buttonToExit = new Button("Exit the LinkedList Program");
        // Add the button to the GridPane
        gridPane.add(buttonToExit, 2, 12, 3, 1);
        // Create a Event Handler for the button to exit to the main menu
        buttonToExit.setOnAction(event ->
        {
            stage.close();
        });
        
        Label labelPhoneDirectory = new Label("Phone Directory"); 
        labelPhoneDirectory.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));
        gridPane.add(labelPhoneDirectory, 1, 13);
        gridPane.add(contactPane, 1, 14);
        
        // Add space to columns via pixels
        gridPane.setVgap(10);
        // Add space to the rows via pixels
        gridPane.setHgap(10);
        // Add padding to the GridPane via pixels
        gridPane.setPadding(new Insets(20));
    }
}// End of the class

