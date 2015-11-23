/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettario;

import java.util.ArrayList;

/**
 *
 * @author DiegoCarlo
 */
public class PrototipoOrdine
{
    ArrayList<PrototipoProdotto> lista;

    public PrototipoOrdine()
    {
        lista = new ArrayList<PrototipoProdotto>();
    }
    public int size()
    {
        return lista.size();
    }
    public PrototipoProdotto get(int i)
    {
        return lista.get(i);
    }
    void test()
    {
        int tot = 5+(int)(Math.random()*5);
        System.out.println(tot);
        for(int i=0; i<tot; i++)
        {
            UnitaDiMisura unitaDiMisura = UnitaDiMisura.NUMERO;
            double random = Math.random();
            double quantita = 1+(int)(random*20);
            if(0.5 > Math.random())
            {
                unitaDiMisura = UnitaDiMisura.KILOGRAMMI;
                quantita = random;
            }
            Quantita q = new Quantita((float)quantita, unitaDiMisura);
                    
            lista.add(new PrototipoProdotto("prodotto"+i, q)); 
        }
    }
}
