/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcmut.cn.appchat.cn_assignment1_applicationchat;
import java.io.File;
import java.awt.FileDialog;
import com.hcmut.cn.appchat.cn_assignment1_applicationchat.SendOutFileThread;
import com.hcmut.cn.appchat.cn_assignment1_applicationchat.ParallelSendThread;
import java.net.Socket;
import com.hcmut.cn.appchat.cn_assignment1_applicationchat.ReceiveThread;
import java.net.ServerSocket;
import javax.swing.JLabel;

/**
 *
 * @author Vu Van Tien
 */
public class ChatWindow extends javax.swing.JFrame {

    /**
     * Creates new form ChatWindow
     */
    
    private ReadClientThread client_read;
    private WriteClientThread client_write;
    private String name;
    private File file;
    private ClientInfo otherInfo;
    private ReceiveThread receiveThread;
    private ServerSocket serverSocket;
    private Socket socketSendFile;
    
    public ChatWindow() {
        initComponents();
        
    }

    public ChatWindow(String client_name) {
        initComponents();
        this.setVisible(true);
        name = client_name;
        this.setTitle(client_name);
    }

    public ChatWindow(Socket socket, String name) {
        this(name);
        
        client_read = new ReadClientThread(socket);
        client_write = new WriteClientThread(socket);
        
        client_read.setTextArea(area_msg_disp);
        client_read.start();
        
    }

    public ChatWindow(ServerSocket serverSocket, Socket socket, String name) {
        this(socket, name);
        this.serverSocket = serverSocket;
        receiveThread = new ReceiveThread(serverSocket, this);
        receiveThread.start();
    }

    public ChatWindow(ServerSocket serverSocket, Socket socketMessage, String name, Socket socketSendFile) {
        this(serverSocket, socketMessage, name);
        // for send message
        this.socketSendFile = socketSendFile;
        
        otherInfo = new ClientInfo(socketSendFile.getInetAddress().getHostAddress(), socketSendFile.getPort());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text_msg = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        btn_browse = new javax.swing.JButton();
        btn_send_file = new javax.swing.JButton();
        filename_label = new javax.swing.JLabel();
        btn_cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area_msg_disp = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btn_send.setText("Send");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        btn_browse.setText("Browse...");
        btn_browse.setName("btn_browse"); // NOI18N
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        btn_send_file.setText("Send file");
        btn_send_file.setEnabled(false);
        btn_send_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send_fileActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.setEnabled(false);
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        area_msg_disp.setEditable(false);
        area_msg_disp.setColumns(20);
        area_msg_disp.setRows(5);
        jScrollPane1.setViewportView(area_msg_disp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(filename_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_send_file, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(text_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_send_file, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                    .addComponent(filename_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        // TODO add your handling code here:
        String str;
        str = text_msg.getText().trim();
        area_msg_disp.append(this.name + ": " + str + "\n");
        
        client_write.write(this.name + ": " + str);
        text_msg.setText("");
    }//GEN-LAST:event_btn_sendActionPerformed

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
        // TODO add your handling code here:
        filename_label.setText("Choose File");
        FileDialog fileDialog = new FileDialog(this);
        fileDialog.setVisible(true);
        String file_name = fileDialog.getFile();
        String file_dir = fileDialog.getDirectory();
        
        filename_label.setText(file_name + " is chosen");
        file = new File(file_dir, file_name);
        
        if (file != null) {
            btn_cancel.setEnabled(true);
            btn_send_file.setEnabled(true);
        }
    }//GEN-LAST:event_btn_browseActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        if (file != null) {
            file.delete();
            file = null;
        }
        
        filename_label.setText("");
        btn_cancel.setEnabled(false);
        btn_send_file.setEnabled(false);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_send_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send_fileActionPerformed
        // TODO add your handling code here:
        
        ParallelSendThread parallelSend = new ParallelSendThread(file, otherInfo);
        parallelSend.start();
        
        // handle 
        String filename = filename_label.getText();
        filename_label.setText(filename + " sent successfully");
        btn_cancel.setEnabled(false);
        btn_send_file.setEnabled(false);
    }//GEN-LAST:event_btn_send_fileActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_msg_disp;
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_send;
    private javax.swing.JButton btn_send_file;
    private javax.swing.JLabel filename_label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField text_msg;
    // End of variables declaration//GEN-END:variables

    JLabel getFilenameLabel() {
        return filename_label;
    }
}
