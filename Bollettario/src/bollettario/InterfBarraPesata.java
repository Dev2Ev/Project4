/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettario;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author DiegoCarlo
 */
public class InterfBarraPesata {
   
    int id;
    JPanel pannello;
    JButton meno;
    JButton piu;
    JToggleButton prodotto;
    JTextField quantita;
    JTextField altro;
    StatoPesata stato;

    public InterfBarraPesata(String nome, String numero, int id)
    {
        this.id = id;
        this.pannello = new javax.swing.JPanel();
        this.meno = new javax.swing.JButton();
        this.prodotto = new javax.swing.JToggleButton();
        this.piu = new javax.swing.JButton();
        this.quantita = new javax.swing.JTextField();
        this.altro = new javax.swing.JTextField();
        this.stato = StatoPesata.INATTIVA;
        initComponents(nome, numero);
    }
    
    public void setStato(StatoPesata stato)
    {
        this.stato = stato;
        switch(stato)
        {
            case ESAURITA:
                pannello.setBackground(new Color(204, 204, 255));
                break;
            case INATTIVA:
                pannello.setBackground(new Color(240,240,240));
                break;
            case FOCUS_ATTIVA:
                pannello.setBackground(new Color(153, 255, 153));
                Bollettario.interfaccia.selezioneFocusPesata(id);
                break;
            case ATTIVA:
                pannello.setBackground(new Color(255, 255, 204));
        }
        
    }
    
    public void initComponents(String nome, String numero)
    {
        prodotto.setText(nome);

        meno.setText("-");

        quantita.setText(numero);

        altro.setText("0");

        piu.setText("+");
        
        pannello.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        javax.swing.GroupLayout pannelloLayout = new javax.swing.GroupLayout(pannello);
        pannello.setLayout(pannelloLayout);
        pannelloLayout.setHorizontalGroup(
            pannelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prodotto, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pannelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantita)
                    .addComponent(altro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pannelloLayout.setVerticalGroup(
            pannelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(piu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(prodotto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloLayout.createSequentialGroup()
                            .addComponent(quantita)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(altro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        prodotto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                switch(stato)
                {
                    case ESAURITA:
                        break;
                    case INATTIVA:
                        break;
                    case FOCUS_ATTIVA:
                        prodotto.setSelected(true);
                        break;
                    case ATTIVA:
                        break;
                }
            }
        });
        prodotto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                switch(stato)
                {
                    case ESAURITA: break;
                    case INATTIVA: 
                        //prodotto.setSelected(true);
                        setStato(StatoPesata.FOCUS_ATTIVA);
                        break;
                    case FOCUS_ATTIVA:
                        //prodotto.setSelected(false);
                        setStato(StatoPesata.INATTIVA);
                        break;
                    case ATTIVA: 
                        setStato(StatoPesata.FOCUS_ATTIVA);
                        break;
                }
            }
        });
    }
}
