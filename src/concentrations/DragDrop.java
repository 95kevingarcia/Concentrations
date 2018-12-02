/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentrations;

/**
 *
 * @author Kevin Garcia
 * This is used in case a person wants to drag and drop of file instead of using the file path
 */
import java.awt.dnd.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;

public class DragDrop extends JDialog implements ActionListener {

    private JTextArea input;
    private JButton finished;
    private javax.swing.JLabel jLabel;

    private javax.swing.JScrollPane jScrollPane;

    public DragDrop() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {

        setTitle("Drag and Drop Excel Files");
        finished = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        jLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(350, 200));
        setResizable(false);

        finished.setText("Go");

        input.setEditable(false);
        input.setColumns(1);
        input.setLineWrap(true);
        input.setRows(1);
        input.setSize(new java.awt.Dimension(240, 200));
        jScrollPane.setViewportView(input);

        jLabel.setText("Input File");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(finished))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel)
                .addGap(13)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13)
                .addComponent(finished)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        finished.addActionListener(this);
        pack();
        // </editor-fold>   
        
        enableDragAndDrop();
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }

    private void enableDragAndDrop() {
        new DropTarget(input, new DropTargetListener() {

            public void dragEnter(DropTargetDragEvent e) {
            }

            public void dragExit(DropTargetEvent e) {
            }

            public void dragOver(DropTargetDragEvent e) {
            }

            public void dropActionChanged(DropTargetDragEvent e) {
            }

            @Override
            public void drop(DropTargetDropEvent e) {
                try {
                    // Accept the drop input, important!
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                    // Get the files that are dropped as java.util.List
                    java.util.List list = (java.util.List) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                    // Now get the input file from the list,
                    File file = (File) list.get(0);
                    input.setText(file.getAbsolutePath());

                } catch (Exception ex) {
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(input.getText().isEmpty() || 
                !(input.getText().endsWith(".xls") || input.getText().endsWith(".xlsx")))
        {
            JOptionPane.showMessageDialog(null, "Error with files added", "Error",
                                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            dispose();
        }
    }
     
    
    
    public String getFile(){
        String file = "";
        if(!input.getText().isEmpty()){
            file = input.getText();
        }
        return file;
    }
}
