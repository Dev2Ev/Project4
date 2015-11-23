/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettario;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.table.TableColumn;

/**
 *
 * @author DiegoCarlo
 */
public class Interfaccia extends javax.swing.JFrame {

    /**
     * Creates new form Interfaccia
     */
    private PrototipoOrdine ordineSelezionato;
    private static ArrayList<InterfBarraPesata> barrePesate;
    public Interfaccia() {
        initComponents();
        initComponents2();
    }

    private void initComponents2()
    {
        initJTable();
        ricercaComboCalendario.setText("");
        initComboCalendario();
        initTime();
        selezionaOrdine();
        initPesate();
    }
    private void selezionaOrdine()
    {
        this.ordineSelezionato = new PrototipoOrdine();
        ordineSelezionato.test();
        creaBarrePesate();
    }
    private void creaBarrePesate()
    {
        barrePesate = new ArrayList<InterfBarraPesata>();
        for(int i=0; i<ordineSelezionato.size(); i++)
        {
            String nome = ordineSelezionato.get(i).idProdotto;
            String quantita = ordineSelezionato.get(i).quantita.toString();
            barrePesate.add(new InterfBarraPesata(nome, quantita, i));
        }
    }
    public static void selezioneFocusPesata(int id)
    {
        for(int i=0; i<barrePesate.size(); i++)
        {
            if(
                    barrePesate.get(i).stato == StatoPesata.FOCUS_ATTIVA &&
                    barrePesate.get(i).id != id
            )
            {
                barrePesate.get(i).setStato(StatoPesata.ATTIVA);
            }
        }
    }
    private void initPesate()
    {
        javax.swing.GroupLayout jPanelSfondoLayout = new javax.swing.GroupLayout(jPanelSfondo);
        jPanelSfondo.setLayout(jPanelSfondoLayout);
        
        ParallelGroup gruppoParallelo2 = jPanelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        for(int i=0; i<barrePesate.size(); i++)
        {
            gruppoParallelo2.addComponent(
                    barrePesate.get(i).pannello,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE);
        }
        
        SequentialGroup gruppoSequenziale = jPanelSfondoLayout.createSequentialGroup();
        gruppoSequenziale.addContainerGap();
        gruppoSequenziale.addGroup(gruppoParallelo2);
        gruppoSequenziale.addContainerGap();
        
        ParallelGroup gruppoParallelo1 = jPanelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        gruppoParallelo1.addGroup(gruppoSequenziale);
        
        jPanelSfondoLayout.setHorizontalGroup(gruppoParallelo1);
        
        SequentialGroup gruppoV2 = jPanelSfondoLayout.createSequentialGroup();
        gruppoV2.addContainerGap();
        for(int i=0; i<barrePesate.size(); i++)
        {
            if(i != 0)
            {
                gruppoV2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
            }
            gruppoV2.addComponent(barrePesate.get(i).pannello,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE);
        }
        gruppoV2.addContainerGap();
        Group gruppoV = jPanelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);
        gruppoV.addGroup(gruppoV2);
        jPanelSfondoLayout.setVerticalGroup(gruppoV);
        
        jScrollPane5.setViewportView(jPanelSfondo);
    }
    private void initTime()
    {
        /*
        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("it"));
        String[] dayNames = symbols.getWeekdays();
        for (String s : dayNames) { 
           System.out.print(s + " ");
        }*/
        Calendar myCal = Calendar.getInstance();
        String oggi = myCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ITALIAN);
        jRadioButtonLunedi.setText("Lunedì");
        jRadioButtonMartedi.setText("Martedì");
        jRadioButtonMercoledi.setText("Mercoledì");
        jRadioButtonGiovedi.setText("Giovedì");
        jRadioButtonVenerdi.setText("Venerdì");
        jRadioButtonSabato.setText("Sabato");
        jRadioButtonDomenica.setText("Domenica");
        switch(oggi)
        {
            case "lunedì":
                jRadioButtonLunedi.setText("LUNEDI");
                jRadioButtonLunedi.setSelected(true);
                break;
            case "martedì":
                jRadioButtonMartedi.setText("MARTEDI");
                jRadioButtonMartedi.setSelected(true);
                break;
            case "mercoledì":
                jRadioButtonMercoledi.setText("MERCOLEDI");
                jRadioButtonMercoledi.setSelected(true);
                break;
            case "giovedì":
                jRadioButtonGiovedi.setText("GIOVEDI");
                jRadioButtonGiovedi.setSelected(true);
                break;
            case "venerdì":
                jRadioButtonVenerdi.setText("VENERDI");
                jRadioButtonVenerdi.setSelected(true);
                break;
            case "sabato":
                jRadioButtonSabato.setText("SABATO");
                jRadioButtonSabato.setSelected(true);
                break;
            case "domenica":
                jRadioButtonDomenica.setText("DOMENICA");
                jRadioButtonDomenica.setSelected(true);break;
        }
        
    }
    private void aggiornaGiorno()
    {
        
    }
    private void initJTable()
    {
        int righe = 25;
        int colonne = 8;
        Object[][] tab = new Object [righe][colonne];
        int oraInizio = 6;
        int incremento = -1;
        for(int i=0; i<righe; i++)
        {
            for(int j=0; j<colonne; j++)
            {
                if(j == 0)
                {
                    String minuti;
                    if(i%2 == 0)
                    {
                        minuti = ":00";
                        incremento++;
                    }
                    else
                    {
                        minuti = ":30";
                    }
                    tab[i][j] = (oraInizio + incremento) + minuti;
                }
            }
        }
        jTable1.setModel(new JTableModel(
            tab,
            new String [] {
                "Ora", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"
            }
        ));
        jTable1.setRowHeight(60);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
    }
    private void aggiornaTab()
    {
        int tabSelected = jTabbedPane.getSelectedIndex();
        switch(tabSelected)
        {
            case 0: aggiornaPannelloOrdini(); break;
            case 1: aggiornaPannelloCalendario(); break;
            case 2: aggiornaPannelloOpzioni(); break;
        }
    }
    private void aggiornaPannelloOrdini()
    {
        
    }
    private void aggiornaPannelloCalendario()
    {
        
    }
    private void initComboCalendario()
    {
        ElencoClienti selezione = new ElencoClienti();
        ElencoClienti el = Bollettario.dataBase.elencoClienti;
        for(int i=0; i<el.size(); i++)
        {
            if(el.get(i).ragioneSociale.toLowerCase().contains(ricercaComboCalendario.getText().toLowerCase()))
            {
                selezione.add(el.get(i));
            }
        }
        ClienteIndicizzato[] comboCalendario = new ClienteIndicizzato[selezione.size()];
        for(int i=0; i<selezione.size(); i++)
        {
            comboCalendario[i] = new ClienteIndicizzato(
                    selezione.get(i).codice,
                    selezione.get(i).ragioneSociale);
        }
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(comboCalendario));
    }
    private void aggiornaPannelloOpzioni()
    {
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelOrdini = new javax.swing.JPanel();
        jRadioButtonLunedi = new javax.swing.JRadioButton();
        jRadioButtonMartedi = new javax.swing.JRadioButton();
        jRadioButtonMercoledi = new javax.swing.JRadioButton();
        jRadioButtonGiovedi = new javax.swing.JRadioButton();
        jRadioButtonVenerdi = new javax.swing.JRadioButton();
        jRadioButtonSabato = new javax.swing.JRadioButton();
        jRadioButtonDomenica = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanelSfondo = new javax.swing.JPanel();
        jPanelCalendario = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox();
        ricercaComboCalendario = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanelOpzioni = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButtonLunedi);
        jRadioButtonLunedi.setText("Lunedì");

        buttonGroup1.add(jRadioButtonMartedi);
        jRadioButtonMartedi.setText("Martedì");

        buttonGroup1.add(jRadioButtonMercoledi);
        jRadioButtonMercoledi.setText("Mercoledì");

        buttonGroup1.add(jRadioButtonGiovedi);
        jRadioButtonGiovedi.setText("Giovedì");

        buttonGroup1.add(jRadioButtonVenerdi);
        jRadioButtonVenerdi.setText("Venerdì");

        buttonGroup1.add(jRadioButtonSabato);
        jRadioButtonSabato.setText("Sabato");

        buttonGroup1.add(jRadioButtonDomenica);
        jRadioButtonDomenica.setText("Domenica");

        jLabel1.setText("Ordini");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Aggiungi Ordine");

        jButton2.setText("Modifica");

        jButton3.setText("Elimina");

        jLabel2.setText("Dettagli Ordine");

        jLabel3.setText("Ora Attuale");

        jLabel4.setText("00 : 00");

        jLabel5.setText("Ora di Consegna");

        jButton4.setText("Stampa");

        jButton5.setText("Tara da Bilancia");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelSfondoLayout = new javax.swing.GroupLayout(jPanelSfondo);
        jPanelSfondo.setLayout(jPanelSfondoLayout);
        jPanelSfondoLayout.setHorizontalGroup(
            jPanelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jPanelSfondoLayout.setVerticalGroup(
            jPanelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jPanelSfondo);

        javax.swing.GroupLayout jPanelOrdiniLayout = new javax.swing.GroupLayout(jPanelOrdini);
        jPanelOrdini.setLayout(jPanelOrdiniLayout);
        jPanelOrdiniLayout.setHorizontalGroup(
            jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                        .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelOrdiniLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)))
                    .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                        .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                                .addComponent(jRadioButtonLunedi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonMartedi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonMercoledi))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                                .addComponent(jRadioButtonGiovedi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonVenerdi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonSabato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonDomenica)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanelOrdiniLayout.setVerticalGroup(
            jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonLunedi)
                    .addComponent(jRadioButtonMartedi)
                    .addComponent(jRadioButtonMercoledi)
                    .addComponent(jRadioButtonGiovedi)
                    .addComponent(jRadioButtonVenerdi)
                    .addComponent(jRadioButtonSabato)
                    .addComponent(jRadioButtonDomenica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                        .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelOrdiniLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addContainerGap())
        );

        jTabbedPane.addTab("Ordini", jPanelOrdini);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ricercaComboCalendario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ricercaComboCalendarioKeyReleased(evt);
            }
        });

        jButton6.setText("x");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCalendarioLayout = new javax.swing.GroupLayout(jPanelCalendario);
        jPanelCalendario.setLayout(jPanelCalendarioLayout);
        jPanelCalendarioLayout.setHorizontalGroup(
            jPanelCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
                    .addGroup(jPanelCalendarioLayout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ricercaComboCalendario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanelCalendarioLayout.setVerticalGroup(
            jPanelCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ricercaComboCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Calendario", jPanelCalendario);

        javax.swing.GroupLayout jPanelOpzioniLayout = new javax.swing.GroupLayout(jPanelOpzioni);
        jPanelOpzioni.setLayout(jPanelOpzioniLayout);
        jPanelOpzioniLayout.setHorizontalGroup(
            jPanelOpzioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
        );
        jPanelOpzioniLayout.setVerticalGroup(
            jPanelOpzioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Opzioni", jPanelOpzioni);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        aggiornaTab();
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private void ricercaComboCalendarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ricercaComboCalendarioKeyReleased
        initComboCalendario();
    }//GEN-LAST:event_ricercaComboCalendarioKeyReleased

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        ricercaComboCalendario.setText("");
        initComboCalendario();
    }//GEN-LAST:event_jButton6MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanelCalendario;
    private javax.swing.JPanel jPanelOpzioni;
    private javax.swing.JPanel jPanelOrdini;
    private javax.swing.JPanel jPanelSfondo;
    private javax.swing.JRadioButton jRadioButtonDomenica;
    private javax.swing.JRadioButton jRadioButtonGiovedi;
    private javax.swing.JRadioButton jRadioButtonLunedi;
    private javax.swing.JRadioButton jRadioButtonMartedi;
    private javax.swing.JRadioButton jRadioButtonMercoledi;
    private javax.swing.JRadioButton jRadioButtonSabato;
    private javax.swing.JRadioButton jRadioButtonVenerdi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField ricercaComboCalendario;
    // End of variables declaration//GEN-END:variables
}
