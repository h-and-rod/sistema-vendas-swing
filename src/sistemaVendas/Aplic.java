/**
 *
 * @author Henrique Garcia
 */

package sistemaVendas;

import javax.swing.*;
import sistemaVendas.view.Menu;

public class Aplic {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu tela = new Menu();
                tela.setVisible(true);
            }
        });
    }
}