/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettario;

/**
 *
 * @author DiegoCarlo
 */
public class PrototipoProdotto
{
    String idProdotto;
    Quantita quantita;

    public PrototipoProdotto(String idProdotto, Quantita quantita) {
        this.idProdotto = idProdotto;
        this.quantita = quantita;
    }
}
