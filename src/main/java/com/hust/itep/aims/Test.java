package com.hust.itep.aims;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;

import com.hust.itep.aims.database.ConnectJDBC;
import com.hust.itep.aims.service.EventCellInputChange;
import com.hust.itep.aims.service.QtyCellEditor;
import com.hust.itep.aims.entity.media.Media;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;



public class Test extends javax.swing.JFrame {
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable table;
    public Test() {
        initComponents();
        testData();
    }
    private void testData() {
//        try {
//            ReportManager.getInstance().compileReport();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        table.getColumnModel().getColumn(3).setCellEditor(new QtyCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged() {
                sumAmount();
            }
        }));
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Connection conn = null;
        try {
            String sql = "select  Media.title, Order_Media.price, Media.category, Order_Media.Quantity\n" +
                    "from Order_Media\n" +
                    "LEFT JOIN media\n" +
                    "on Order_Media.mediaId = media.id\n" +
                    "where orderId = 1\n";
            conn = ConnectJDBC.getConnection();
            Statement stmt = conn.createStatement();
            // Get data
            ResultSet rs = stmt.executeQuery(sql);
            int i = 1;
            int sum = 0;
            while (rs.next()) {
                model.addRow(new Media(i,
                        rs.getString("title"),
                        rs.getInt("price"),
                        rs.getString("category"),
                        rs.getInt("quantity")).toTableRow(table.getRowCount() + 1));
                i++;
            }
            sumAmount();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                    System.out.println("Close successful !");
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void sumAmount() {
        int total = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            Media item = (Media) table.getValueAt(i, 0);
            total += item.getPrice()*item.getQuantity();
        }
        DecimalFormat df = new DecimalFormat("#,##0.00");
        lbTotal.setText(df.format(total)+" VND");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Data", "No", "Name", "Category", "Price", "Quantity", "Total Price"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(1).setPreferredWidth(30);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        jLabel1.setText("Total");

        lbTotal.setText("0.0");

        jButton1.setText("Payment");
//        jButton1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton1ActionPerformed(evt);
//            }
//        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
//                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(97, 97, 97)
                                                                .addComponent(lbTotal)))
                                                .addGap(8, 8, 8)))
//                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(lbTotal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

//    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
//        try {
//            List<FieldReportPayment> fields = new ArrayList<>();
//            for (int i = 0; i < table.getRowCount(); i++) {
//                Media data = (Media) table.getValueAt(i, 0);
//
//                fields.add(new FieldReportPayment(data.getTitle(), data.getPrice(),data.getCategory(), data.getQuantity()));
//            }
//            ParameterReportPayment dataprint = new ParameterReportPayment("Admin", "MR A", lbTotal.getText(), generateQrcode(), fields);
//            ReportManager.getInstance().printReportPayment(dataprint);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }//GEN-LAST:event_jButton1ActionPerformed

//    private InputStream generateQrcode() throws WriterException, IOException {
//        NumberFormat nf = new DecimalFormat("0000000");
//        Random ran = new Random();
//        String invoice = nf.format(ran.nextInt(9999999) + 1);
//        Map<EncodeHintType, Object> hints = new HashMap<>();
//        hints.put(EncodeHintType.MARGIN, 0);
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(invoice, BarcodeFormat.QR_CODE, 100, 100, hints);
//        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", outputStream);
//        return new ByteArrayInputStream(outputStream.toByteArray());
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("style");
        FlatDarculaLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

}
