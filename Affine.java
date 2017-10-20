import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
/*This program complies with the JMU honor Code
 * This project works per the specifications
 Submitted by: Thomas Huffman*/

public class Affine
{
  private JFrame frame;
  JMenuItem close = new JMenuItem("Exit");
  JMenuItem encrypt = new JMenuItem("Encryption Mode");
  JMenuItem decrypt = new JMenuItem("Decryption Mode");
  JTextField userText = new JTextField(1);
  JTextField userText1 = new JTextField(1);
  JMenuBar menuBar;
  JTextArea textArea = new JTextArea("", 20, 50);
  JLabel ins = new JLabel("Please enter two numbers a & b seperated by a space in the LOWER text field then choose a mode and then a text file to be encrypted or decrypted");
  JLabel enc = new JLabel(" ");
  JLabel dyc = new JLabel("Please choose a file for decryption");
  JLabel laba = new JLabel("A");
  JLabel labb = new JLabel("B");
  String use;
  int nultest;
  int fileError;
  String as;
  int a;
  String bs;
  int b;
  JFileChooser fc = new JFileChooser();
  BufferedReader bufferedReader1;
  File file1;
  char numrep;
  String encS = "";
  private class ButtonPress implements ActionListener
  {
    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource() == close)
      {
        frame.setVisible(false);
        frame.dispose();
      }
      else if(e.getSource() == encrypt)
      {
        fileError = 0;
        nultest = 0;
        dyc.setVisible(false);
        ins.setVisible(false);
        enc.setVisible(true);
        textArea.setText("");
        use = userText.getText();
        if(use.contains(" "))
        {
          as = use.substring(0, use.indexOf(" "));
          bs = use.substring(use.indexOf(" "), use.length());
        }
        bs = bs.substring(1, bs.length());
        if(as != null && as.matches("[-+]?\\d*\\.?\\d+"))
        {
          a = Integer.parseInt(as);
        }
        else 
        {
          nultest = 1;
          textArea.setText("Please enter the numbers correctly");
        }
        if(bs != null && bs.matches("[-+]?\\d*\\.?\\d+"))
        {
          b = Integer.parseInt(bs);
        }
        else
        {
          nultest = 1;
          textArea.setText("Please enter the numbers correctly");
        }
        if(!euclid(a, 26) && as.matches("[-+]?\\d*\\.?\\d+") && bs.matches("[-+]?\\d*\\.?\\d+"))
        {
          nultest = 1;
          textArea.setText("The value " + a + " is invalid for 'a' because the gcd of " + a  + " and 26 does not equal 1");
        }
        if(nultest == 0)
        {
          
        int returnValue = fc.showOpenDialog(encrypt);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          file1 = file;
          FileReader fileReader = null;
          try
          {
            fileReader = new FileReader(file);
          }
          catch (FileNotFoundException e3)
          {
            // TODO Auto-generated catch block
            e3.printStackTrace();
      }
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          bufferedReader1 = bufferedReader;
          String inputFile = "";
          String textFieldReadable = "";
          try
          {
            textFieldReadable = bufferedReader.readLine();
          }
          catch (IOException e2)
          {
            // TODO Auto-generated catch block
            e2.printStackTrace();
          }
          while (textFieldReadable != null){
            textFieldReadable += "\n";
            inputFile += textFieldReadable;
            try
            {
              textFieldReadable = bufferedReader.readLine();
              //textFieldReadable += "\n";
            }
            catch (IOException e1)
            {
              e1.printStackTrace();
            }                    
        }
          encS = "";
          for(int i = 0; i < inputFile.length(); i++)
          {
            numrep = inputFile.charAt(i);
            if(Character.isLetter(numrep) && Character.isUpperCase(numrep))
            {
              numrep = (char) ((a * (numrep - 'A') + b) % 26 + 'A');
            }
            else if(Character.isLetter(numrep))
            {
              numrep = (char) ((a * (numrep - 'a') + b) % 26 + 'a');
            }
            else if(numrep == ' ' || numrep == '\n')
            {
              
            }
            else 
            {
              textArea.setText("A character in the file is not a letter or a white space" );
              fileError = 1;
              break;
              
            }
            encS += numrep;
          }
          if(fileError == 1)
          {
            
          }
          else
          {
            textArea.append(inputFile.toLowerCase());
            textArea.append("\n");
            textArea.append(encS.toUpperCase());
          }
          
      }
        else {
        }
          textArea.setCaretPosition(textArea.getDocument().getLength());
      }
      }
      else if(e.getSource() == decrypt)
      {
        fileError = 0;
        nultest = 0;
        enc.setVisible(false);
        ins.setVisible(false);
        dyc.setVisible(true);
        textArea.setText("");
        use = userText.getText();
        if(use.contains(" "))
        {
          as = use.substring(0, use.indexOf(" "));
          bs = use.substring(use.indexOf(" "), use.length());
        }
        bs = bs.substring(1, bs.length());
        if(as != null && as.matches("[-+]?\\d*\\.?\\d+"))
        {
          a = Integer.parseInt(as);
        }
        else 
        {
          nultest = 1;
          textArea.append("Please enter the numbers correctly");
        }
        if(bs != null && bs.matches("[-+]?\\d*\\.?\\d+"))
        {
          b = Integer.parseInt(bs);
        }
        else
        {
          nultest = 1;
          textArea.append("Please enter the numbers correctly");
        }
        if(!euclid(a, 26) && as.matches("[-+]?\\d*\\.?\\d+") && bs.matches("[-+]?\\d*\\.?\\d+"))
        {
          nultest = 1;
          textArea.append("The value " + a + " is invalid for 'a' because the gcd of " + a + " and 26 does not equal 1");
        }
        if(nultest == 0)
        {
        int returnValue = fc.showOpenDialog(encrypt);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          file1 = file;
          FileReader fileReader = null;
          try
          {
            fileReader = new FileReader(file);
          }
          catch (FileNotFoundException e3)
          {
            // TODO Auto-generated catch block
            e3.printStackTrace();
      }
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          bufferedReader1 = bufferedReader;
          String inputFile = "";
          String textFieldReadable = "";
          try
          {
            textFieldReadable = bufferedReader.readLine();
          }
          catch (IOException e2)
          {
            // TODO Auto-generated catch block
            e2.printStackTrace();
          }
          while (textFieldReadable != null){
            textFieldReadable += "\n";
            inputFile += textFieldReadable;
            try
            {
              textFieldReadable = bufferedReader.readLine();
              //textFieldReadable += "\n";
            }
            catch (IOException e1)
            {
              e1.printStackTrace();
            }                    
        }
          BigInteger inverse = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26));
          encS = "";
          for(int i = 0; i < inputFile.length(); i++)
          {
            numrep = inputFile.charAt(i);
            if(Character.isLetter(numrep) && Character.isUpperCase(numrep))
            {
              int dec = inverse.intValue() * (numrep - 'A' - b + 26);
              numrep = (char)(dec % 26 + 'A');
            }
            else if(Character.isLetter(numrep))
            {
              int dec = inverse.intValue() * (numrep - 'a' - b + 26);
              numrep = (char) (dec % 26 + 'a');
              
            }
            else if(numrep == ' ' || numrep == '\n')
            {
              
            }
            else 
            {
              textArea.setText("A character in the file is not a letter or a white space" );
              fileError = 1;
              break;
              
            }
            encS += numrep;
          }
          if(fileError == 1)
          {
            
          }
          else
          {
            textArea.append(inputFile);
            textArea.append("\n");
            textArea.append(encS.toUpperCase());
          }
      }
      }
      }
    }
  }
  public Affine()
  {
    ins.setVisible(true);
    //enc.setVisible(false);
    dyc.setVisible(false);
    frame = new JFrame("Affine");
    menuBar = new JMenuBar();
    JMenu menu = new JMenu("Modes");
    JPanel upperPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    upperPanel.add(enc, BorderLayout.NORTH);
    upperPanel.add(dyc, BorderLayout.NORTH);
    upperPanel.add(ins, BorderLayout.NORTH);
    //laba.setLocation(1, 1);
    //upperPanel.add(laba);
    frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
    //upperPanel.add(textArea, BorderLayout.CENTER);
    frame.getContentPane().add(middlePanel, BorderLayout.CENTER);
    middlePanel.add(textArea, BorderLayout.CENTER);
    menu.add(close);
    menu.add(encrypt);
    menu.add(decrypt);
    menuBar.add(menu);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    close.addActionListener(new ButtonPress());
    frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setJMenuBar(menuBar);
    textArea.setLineWrap(true);
    //frame.add(userText);
    frame.add(userText, BorderLayout.SOUTH);
    //frame.add(userText1);
    close.addActionListener(new ButtonPress());
    encrypt.addActionListener(new ButtonPress());
    decrypt.addActionListener(new ButtonPress());
  }
  public static void main(String[] args)
  {
    new Affine().display();
  }
  private void display()
  {
    frame.pack();
    frame.setVisible(true);  
  }
  public boolean euclid(int p, int q)
  {
    while (q != 0)
    {
      int euc = q; 
      q = p % q;
      p = euc;
    }
    if(p != 1)
      return false;
    else 
      return true;
    
  }
}
