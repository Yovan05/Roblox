/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package mx.itson.roblox.views.tickets;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mx.itson.roblox.entities.Ticket;
import mx.itson.roblox.models.ShowModel;
import mx.itson.roblox.models.TicketModel;

/**
 *
 * @author alexi
 */
public class Tickets extends javax.swing.JDialog {

    List<Ticket> tickets = new ArrayList();
    int row=-1;
    
    /**
     * Show, delete, edit and register shows from the database
     */
    public Tickets(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tickets=TicketModel.getAll("");
        updateTable();
    }
    
    /**
     * Fills the tblClients table
     */
    public void updateTable(){
        DefaultTableModel model = (DefaultTableModel) tblTickets.getModel();
        model.setNumRows(0);
        int nList = 0;
        for(Ticket t : tickets){
            nList ++;
            model.addRow(new Object[]{
                t.getId(), t.getClient().getName(),t.getShow().getMovie().getName(), t.getSeat(), t.getPrice()
            });
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

        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTickets = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txfSearch = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnClient = new javax.swing.JMenu();
        jmnEmployee = new javax.swing.JMenu();
        jmnMovie = new javax.swing.JMenu();
        jmnRoom = new javax.swing.JMenu();
        jmnShow = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAdd.setText("Añadir");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnDelete.setText("Eliminar");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        btnUpdate.setText("Editar");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Boletos");

        tblTickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Cliente", "Pelicula", "Asiento", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTickets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTicketsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTickets);
        if (tblTickets.getColumnModel().getColumnCount() > 0) {
            tblTickets.getColumnModel().getColumn(0).setMinWidth(0);
            tblTickets.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblTickets.getColumnModel().getColumn(0).setMaxWidth(0);
            tblTickets.getColumnModel().getColumn(1).setResizable(false);
            tblTickets.getColumnModel().getColumn(2).setResizable(false);
            tblTickets.getColumnModel().getColumn(3).setResizable(false);
            tblTickets.getColumnModel().getColumn(4).setResizable(false);
        }

        btnBuscar.setText("Buscar");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });

        jmnClient.setText("Clientes");
        jMenuBar1.add(jmnClient);

        jmnEmployee.setText("Empleados");
        jMenuBar1.add(jmnEmployee);

        jmnMovie.setText("Peliculas");
        jMenuBar1.add(jmnMovie);

        jmnRoom.setText("Salas");
        jMenuBar1.add(jmnRoom);

        jmnShow.setText("Funciones");
        jMenuBar1.add(jmnShow);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnAdd))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        CreateTicket createTicket = new CreateTicket(null, true);
        setVisible(false);
        createTicket.setVisible(true);
        tickets =TicketModel.getAll("");
        updateTable();
        setVisible(true);
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        if(row!=-1){
            int id =Integer.parseInt(String.valueOf(tblTickets.getValueAt(row, 0)));
            TicketModel.delete(id);
            tickets=TicketModel.getAll(txfSearch.getText());
            updateTable();
            row=-1;
        }else{
            JOptionPane.showMessageDialog(this, "Selecciona un cliente");
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    
    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        UpdateTicket updateTicket = new UpdateTicket(null, true);
        if(row!=-1){
            int id =Integer.parseInt(String.valueOf(tblTickets.getValueAt(row, 0)));
            TicketModel.getTicket(id);

            Ticket ticket = TicketModel.getTicket(id);

            updateTicket.setTicket(ticket);
            setVisible(false);
            updateTicket.setVisible(true);
            tickets =TicketModel.getAll("");
            updateTable();
            row=-1;
            setVisible(true);

        }else{
            JOptionPane.showMessageDialog(this, "Selecciona un cliente");
        }

    }//GEN-LAST:event_btnUpdateMouseClicked

    private void tblTicketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTicketsMouseClicked
        row = tblTickets.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tblTicketsMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        tickets=TicketModel.getAll(txfSearch.getText());
        updateTable();
    }//GEN-LAST:event_btnBuscarMouseClicked

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
            java.util.logging.Logger.getLogger(Tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tickets dialog = new Tickets(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu jmnClient;
    private javax.swing.JMenu jmnEmployee;
    private javax.swing.JMenu jmnMovie;
    private javax.swing.JMenu jmnRoom;
    private javax.swing.JMenu jmnShow;
    private javax.swing.JTable tblTickets;
    private javax.swing.JTextField txfSearch;
    // End of variables declaration//GEN-END:variables
}