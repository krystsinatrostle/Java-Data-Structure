/*
 * Created by Krystsina Trostle
 *
 * Created on June 3, 2020
 *
 * The objective of the program is to define the GUI main class that
 * consists of the main menu of data structures letting the user choose
 * which data structure to create        
 * 
 */
package Java2_Assign3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContactInformationGraphicalInterface extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception
     {
        GridPane gridPane = new GridPane();
        buildComponents(gridPane);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setHeight(410);
        stage.setWidth(600);
        stage.setTitle("Data Structure Application Contact Names");
        stage.show();
    }
    
    private void buildComponents(GridPane gridPane)
    {
        // Define the label for Sets
        Label labelSets = new Label("Sets"); 
        // Add the label to the GridPane and set font
        gridPane.add(labelSets, 0, 0);
        labelSets.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        
        // Define the label for a Set
        Label labelForSet = new Label("Create and Work with a Set "); 
        // Add the label to the GridPane
        gridPane.add(labelForSet, 1, 1);
        
        Button buttonForSet = new Button("Click this Button to "
                + "Work with a Set");
        // Add the button the the GridPane
        gridPane.add(buttonForSet, 2, 1);
 
        // Define the label for a HashSet
        Label labelForHashSet = new Label("Create and Work with a HashSet "); 
        // Add the label to the GridPane
        gridPane.add(labelForHashSet, 1, 2);
        
        Button buttonForHashSet = new Button("Click this Button to "
                + "Work with a HashSet");
        // Add the button the the GridPane
        gridPane.add(buttonForHashSet, 2, 2);
        
        // Define the label for Lists
        Label labelLists = new Label 
              ("Lists"); 
        // Add the label to the GridPane and set font
        gridPane.add(labelLists, 0, 3);
        labelLists.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        
        // Define the label for an ArrayList
        Label labelForArrayList = new Label("Create and Work with "
                + "an ArrayList "); 
        // Add the label to the GridPane
        gridPane.add(labelForArrayList, 1, 4);
        
        Button buttonForArrayList = new Button("Click this Button to "
                + "Work with an ArrayList");
        // Add the button the the GridPane
        gridPane.add(buttonForArrayList, 2, 4);
        
        // Define the label for a LinkedList
        Label labelForLinkedList = new Label("Create and Work with "
                + "a LinkedList "); 
        // Add the label to the GridPane
        gridPane.add(labelForLinkedList, 1, 5);
        
        Button buttonForLinkedList = new Button("Click this Button to "
                + "Work with a LinkedList");
        // Add the button the the GridPane
        gridPane.add(buttonForLinkedList, 2, 5);
        
        // Define the label for Stacks
        Label labelStacks = new Label("Stacks"); 
        // Add the label to the GridPane and set font
        gridPane.add(labelStacks, 0, 6);
        labelStacks.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        
        // Define the label for a Stack
        Label labelForStack = new Label("Create and Work with a Stack "); 
        // Add the label to the GridPane
        gridPane.add(labelForStack, 1, 7);
        
        Button buttonForStack = new Button("Click this Button to "
                + "Work with a Stack");
        // Add the button the the GridPane
        gridPane.add(buttonForStack, 2, 7);
        
        // Define the label for Queues
        Label labelQueues = new Label 
              ("Queues"); 
        // Add the label to the GridPane
        gridPane.add(labelQueues, 0, 8);
        labelQueues.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        
        // Define the label for a Queue
        Label labelForQueue= new Label 
              ("Create and Work with a Queue "); 
        // Add the label to the GridPane
        gridPane.add(labelForQueue, 1, 9);
        
        Button buttonForQueue = new Button("Click this Button to "
                                               + "Work with a Queue");
        // Add the button the the GridPane
        gridPane.add(buttonForQueue, 2, 9);

        // Add space to columns via pixels
        gridPane.setVgap(8);
        // Add space to the rows via pixels
        gridPane.setHgap(8);
        // Add padding to the GridPane via pixels
        gridPane.setPadding(new Insets(15));
        
        // Buttons to oepn a new window to work with a data structure
        // user picks
        buttonForSet.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {  
                WorkWithSet setContacts = new WorkWithSet();
            }
        });
        
        buttonForHashSet.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {  
                WorkWithHashSet hashSetContacts = new WorkWithHashSet();
            }
        });
        
        buttonForArrayList.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {  
                WorkWithArrayList arrayListContacts = new WorkWithArrayList();
            }
        });
        
        buttonForLinkedList.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {  
                WorkWithLinkedList linkedListContacts = new WorkWithLinkedList();
            }
        });
        
        buttonForStack.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {  
                WorkWithStacks stackContacts = new WorkWithStacks();
            }
        });
        
        buttonForQueue.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {  
                WorkWithQueue queueContacts = new WorkWithQueue();
            }
        });
        
        // Create a button to exit the main menu
        Button buttonToExit = new Button("Exit ");
        // Add the button to the GridPane
        gridPane.add(buttonToExit, 0, 10);
        // Create a Event Handler for the button to exit
        buttonToExit.setOnAction(event ->
        {
            System.exit(0);
        });
    }
}// End of the class
         

