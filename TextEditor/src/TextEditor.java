import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.Font;




public class TextEditor implements ActionListener{
    // declaring properties of testEdtor
JFrame frame;

JMenuBar menuBar;

JMenu file,edit;

JMenuItem newFile, openFile,saveFile;

JMenuItem cut,copy, paste, selectAll,close,delete, print;

JTextArea textArea;
    TextEditor(){

        textArea=new JTextArea();

        menuBar=new JMenuBar();



        frame=new JFrame();

        file=new JMenu("File");
        edit=new JMenu("Edit");

        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);



        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        delete=new JMenuItem("delete");
        print=new JMenuItem("Print");
        //add action Listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);;
        paste.addActionListener(this);;
        selectAll.addActionListener(this);;
        close.addActionListener(this);
        delete.addActionListener(this);
        print.addActionListener(this);
        // adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        edit.add(delete);
        edit.add(print);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        // create  a content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5 ,5 ,5 ,5 ));
        panel.setLayout(new BorderLayout(0,0));
        // add tet area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // create Scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // add scroll pane to panel
        panel.add(scrollPane);

        // add panel to frame
        frame.add(panel);




         // set dimesion of fname
        frame.setBounds(100,100,500,500);
        frame.setTitle("Text Editor");

        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            // perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perfom copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            // perfom paste opeation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll) {
            //perform select all opearion
            textArea.selectAll();
        }
        if(actionEvent.getSource()==delete){

          textArea.cut();
        }
        if(actionEvent.getSource()==print){


                // print the file
            try {
                textArea.print();
            } catch (PrinterException e) {
                throw new RuntimeException(e);
            }
        }
        if(actionEvent.getSource()==close){
            //perform close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file choose
            JFileChooser fileChooser=new JFileChooser("E:");
            int chooseoption=fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chooseoption==JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file=fileChooser.getSelectedFile();

                String filePath=file.getPath();
                try{
                    FileReader fileReader=new FileReader(filePath);

                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String immtermediate="", output="";

                    while((immtermediate=bufferedReader.readLine())!=null){
                        output+=immtermediate+"\n";
                    }
                    // set the output tring to tect area
                    textArea.setText(output );
                }catch (FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            // Intialize file picker;
            JFileChooser fileChooser=new JFileChooser("E");
            int chooseoption=fileChooser.showSaveDialog(null);
            // checl if we clicked on save botton
            if(chooseoption==JFileChooser.APPROVE_OPTION){
                // create a new file with choose directory ppatr ans file name;
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                  FileWriter fileWriter=new FileWriter(file);
                  //Initarailize buffered write
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    // write contents of tect area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newtextEditor=new TextEditor();
        }

    }
    public static void main(String[] args){
        TextEditor editor=new TextEditor();

    }
}