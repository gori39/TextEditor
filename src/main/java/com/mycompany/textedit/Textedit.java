/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.textedit;

/**
 *
 * @author Lenovo
 */
import java.awt.*; //event handling part , for opening event like eg open tab,setting button ( to capture the moment exactly by awt)
import javax.swing.*; //display part ,actionlistner//gui part button are swing component and action is a part of awt.
import java.io.*;  //action listner is something mouse input of user whenever a user click certain element on the program than it has to do accordingly
import java.awt.event.*; //io ba
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.metal.*; //theme  which is available in swing library
import javax.swing.text.*;
class Textedit extends JFrame implements ActionListener{ // clicking of open is an acion event

    JFrame f; // area 
    
    JTextArea t; // wirte , copy paste delete
     
    Textedit(){ //intialize textarea and frame
       f = new JFrame("Notepad"); //intializing the frame with title notepad
       
       //setting the overall theme of the application
       try{
         UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
         
         MetalLookAndFeel.setCurrentTheme(new OceanTheme());
       }
       catch(Exception e){
           
       }
       //create the text component
        t= new JTextArea();
        
        //create the menubar
        JMenuBar menu = new JMenuBar();
        
        JMenu file = new JMenu("File");
        //menu items for file menu
        JMenuItem m1 = new JMenuItem("New");  // buttons
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");
        
        //now add action listener to the menu item //
        
        m1.addActionListener(this);//when new will click the action listenerr willbe triggered , this is adding functionality
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        //now add this in file menu
        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);
        //create edit 
        JMenu edit = new JMenu("Edit");
        
        JMenuItem m5 = new JMenuItem("Cut");  // buttons
        JMenuItem m6= new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");
        
        
        //now add action listener to the menu item //
        
        m5.addActionListener(this);//when new will click the action listenerr willbe triggered , this is adding functionality
        m6.addActionListener(this);
        m7.addActionListener(this);
        
        //now add this in edit menue
        edit.add(m5);
        edit.add(m6);
        edit.add(m7);
        
        //straight away added to the menubar
       JMenuItem close = new JMenuItem("Close");
       close.addActionListener(this);  //close button
       //add all menuitem in menubar
       menu.add(file);
       menu.add(edit);
       menu.add(close);
       //add menu bar in frame
       f.setJMenuBar(menu); //inbuilt function in swing
       f.add(t); //added text area
       f.setSize(1600,900);
       f.show();
    }
    
    ///for adding the functionality to each of the menuitem
    //whenever the button will press this will straight away will create acton event and this fuction will be called by an action listener
    public void actionPerformed(ActionEvent e){
        //extracting the button press in the form of string
        String s = e.getActionCommand();
        
        if(s.equals("New")){
            t.setText(""); ////this functionality is provided javax.swing.text;
        }
        else if(s.equals("Open")){
            //initializing the jfilechooser with desired directory jfilechooser act as a poninter to file location in the hardisk
            JFileChooser j = new JFileChooser("D:"); //starting directory of file chooser object will go directly in d directory
            //invoking the opendialogbox with an integer
            
            int r=j.showOpenDialog(null);//intializing with integer so that i can know the state of open dialog box if it is click or not
            //if user click on open that means it is an approve option ,r us a state
            
            if(r == JFileChooser.APPROVE_OPTION){
              //set the lavel to the path of the selected file location
                File fi = new File(j.getSelectedFile().getAbsolutePath());//if user click on ok that means i have to open that perticular file at j locaton
            //getabsolute gets the whole locaton of the file so that file variable can process it properly
            //now copy the content of file and paste in text Area
            //now if i have open the file now i have to copy the content of the selected file
            //so i will store it in two string
            //for that we need to string with blank decleration
            try{
                String s1 ="",s2="";
            
            FileReader fr =new FileReader(fi);; //it gets the bits from the file , it contains the raw data from the file
                
                       
               
            BufferedReader br = new BufferedReader(fr);  //it extracts the bits, it reads text from a character like this filereader is an input stream 
            //we will read line by line through buffer reader
            
                
                    s2 = br.readLine(); //so i have the first line of the file that i want to write
                
                //now i will use a loop so that i can append all the remaining lines ahead of s2
                while((s1=br.readLine())!=null){ //this means if we the last line is null that means all the lines have been added in s2 and the loop will be terminted
                    s2=s2+'\n'+s1;
                }
                t.setText(s2); //s2 will be shown in text area
            }
            catch(Exception et){
                JOptionPane.showMessageDialog(f, et.getMessage()); //if any error is there it will be displayed 
            }
            }
        }
        else if(s.equals("Save")){
            
            JFileChooser j = new JFileChooser("D:");
            //user has selected where to save the file
            int r = j.showSaveDialog(null);
            
            if(r==JFileChooser.APPROVE_OPTION){
            
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try{
                FileWriter fw = new FileWriter(fi,false); //botoom to up
                
                BufferedWriter bw = new BufferedWriter(fw); //stores the character in buffer and extracts them one by one
                
                bw.write(t.getText());
                
                //after writing the finished flush the stream
                //we dont want bufferred to be fulled and if it hold it previous it cant hold new so we need to flush the old one
                bw.flush();
                bw.close();
                }
                catch(Exception et){
                JOptionPane.showMessageDialog(f, et.getMessage()); //if any error is there it will be displayed 
            }
                
            }
            
        }
        else if(s.equals("Print")){
            try {
                t.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Textedit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(s.equals("Cut")){
            t.cut(); //this functionality is provided javax.swing.text;
        }
        else if(s.equals("Copy")){
            t.copy(); //this functionality is provided javax.swing.text;
        }
        else if(s.equals("Paste")){
            t.paste(); //this functionality is provided javax.swing.text;
        }
        else if(s.equals("Close")){
            f.setVisible(false);
        }
        
    }
    
    public static void main(String[] args) {
        Textedit e = new Textedit();
    }
}
