/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettario;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author DiegoCarlo
 */
public class Bollettario {

    public static Interfaccia interfaccia;
    public static DataBase dataBase;
    public static String nomeDB = "DataBaseGF";
    public static String estensione = ".dbn";
    public static void main(String[] args) {
        caricaStile();
        dataBase = new DataBase();
        carica();
        interfaccia = new Interfaccia();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                interfaccia.setVisible(true);
            }
        });
    }
    static private void caricaStile()
    {
        try
        {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } 
        catch(UnsupportedLookAndFeelException e) {
        // handle exception
        }
        catch (ClassNotFoundException e) {
           // handle exception
        }
        catch (InstantiationException e) {
           // handle exception
        }
        catch (IllegalAccessException e) {
           // handle exception
        }
    }
    public static void salva()
    {
        OutputFile.scrivi(nomeDB+estensione, dataBase);
    }
    public static void carica()
    {
        try
        {
            dataBase = (DataBase)InputFile.leggi(nomeDB+estensione);
            if(dataBase == null)
            {
                dataBase = new DataBase();
                dataBase.elencoClienti.setArrayList(TestDati.creaClienti());
            }   
        }
        catch(ClassNotFoundException c)
        {
        }
    }
    
}
