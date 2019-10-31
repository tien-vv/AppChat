/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcmut.cn.appchat.cn_assignment1_applicationchat;


import java.util.List;
import com.hcmut.cn.appchat.cn_assignment1_applicationchat.ServerSocketThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.hcmut.cn.appchat.cn_assignment1_applicationchat.ClientInfo;
import com.hcmut.cn.appchat.cn_assignment1_applicationchat.ChatWindow;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.Timer;
/**
 *
 * @author nguye
 */
public class ListClientUI extends javax.swing.JFrame {
    private List<ClientInfo> listClient;
    private Socket socket;
    private static ChatClient chatClient;
    private ServerSocket serverSocket;
    /**
     * Creates new form ListClientUI
     */
    public ListClientUI(ChatClient chatClient) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        this.setVisible(true);
        this.chatClient = chatClient;
        this.listClient = this.chatClient.getClientList();
        
        offlineUserList.setEnabled(false);
        
        DefaultListModel modelForOnlineUsers = new DefaultListModel();
        DefaultListModel modelForOfflineUsers = new DefaultListModel();
                        
        onlineUserList.setModel(modelForOnlineUsers);
        offlineUserList.setModel(modelForOfflineUsers);
        
        Timer refreshList = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                modelForOnlineUsers.removeAllElements();
                modelForOfflineUsers.removeAllElements();
                
                List<ClientInfo> listC = chatClient.getClientList();
                
                for (int i = 0; i < listC.size(); i++) {
                    boolean userStatus = listC.get(i).getActiveStatus();
                    if (userStatus) {
                        modelForOnlineUsers.addElement(listC.get(i).getDisplayedName() + "(" + listC.get(i).getUsername() + ")");
                    }
                    else modelForOfflineUsers.addElement(listC.get(i).getDisplayedName() + "(" + listC.get(i).getUsername() + ")");
                }               
            }
        });
        refreshList.start();
                                
        try {
            serverSocket = new ServerSocket(chatClient.getMyPort());
            ServerSocketThread serverSocketThread = new ServerSocketThread(serverSocket);
            serverSocketThread.start();
        } catch (IOException ex) {
            Logger.getLogger(ListClientUI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        onlineUserList = new javax.swing.JList<>();
        onlineUsersLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        offlineUserList = new javax.swing.JList<>();
        offlineUsersLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        onlineUserList.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        onlineUserList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        onlineUserList.setVisibleRowCount(-1);
        onlineUserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onlineUserListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(onlineUserList);

        onlineUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        onlineUsersLabel.setText("Let chat to people down here");
        onlineUsersLabel.setPreferredSize(new java.awt.Dimension(160, 40));

        offlineUserList.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        offlineUserList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(offlineUserList);

        offlineUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        offlineUsersLabel.setText("Oops, people down here are not available");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(onlineUsersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(offlineUsersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(onlineUsersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(offlineUsersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onlineUserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlineUserListMouseClicked
        // TODO add your handling code here:
        if (onlineUserList.getSelectedIndex() != -1) {
            ClientInfo otherInfo = this.listClient.get(onlineUserList.getSelectedIndex());
            
            try {
                Socket socketToOther = new Socket(otherInfo.getHost(), otherInfo.getPort());

                ChatWindowThread chatWindowThread = new ChatWindowThread(
                        this.serverSocket,
                        socketToOther,
                        socketToOther
                );
                                
                chatWindowThread.start();
            } catch (IOException ex) {
                Logger.getLogger(ListClientUI.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        
        onlineUserList.clearSelection();
    }//GEN-LAST:event_onlineUserListMouseClicked

    public void listen(int port) {
        ServerSocket serverSocket;
        ServerSocketThread serverSocketThread;
        
        try {
            serverSocket = new ServerSocket(port);
            serverSocketThread = new ServerSocketThread(serverSocket);
            serverSocketThread.start();

            Socket socketReturn;
            
            while (true) {
                if (serverSocketThread.getSocket() != null) {
                    socketReturn = serverSocketThread.getSocket();
                    serverSocketThread.setNULL();
                    ClientInfo otherClient = new ClientInfo(socketReturn.getInetAddress().getHostAddress(), socketReturn.getPort());                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ListClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
            java.util.logging.Logger.getLogger(ListClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListClientUI(chatClient).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> offlineUserList;
    private javax.swing.JLabel offlineUsersLabel;
    private javax.swing.JList<String> onlineUserList;
    private javax.swing.JLabel onlineUsersLabel;
    // End of variables declaration//GEN-END:variables
}
