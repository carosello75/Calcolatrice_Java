// Importo le librerie Swing e AWT per l'interfaccia grafica
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcolatriceGrafica extends JFrame implements ActionListener {

    // Campo di testo dove appariranno numeri e risultati
    private JTextField display;

    // Variabili per memorizzare i numeri e l'operazione
    private double numero1 = 0, numero2 = 0, risultato = 0;
    private char operatore;

    // Costruttore: imposta la finestra e i componenti
    public CalcolatriceGrafica() {
        // Titolo della finestra
        setTitle("Calcolatrice Java");
        // Dimensioni iniziali della finestra
        setSize(350, 500);
        // Centra la finestra sullo schermo
        setLocationRelativeTo(null);
        // Chiusura del programma alla chiusura della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Layout manager: BorderLayout per dividere l’interfaccia
        setLayout(new BorderLayout());

        // === DISPLAY ===
        display = new JTextField();
        display.setFont(new Font("Consolas", Font.BOLD, 28));
        display.setEditable(false); // non modificabile direttamente dall'utente
        display.setBackground(Color.WHITE);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        // === PANNELLO PULSANTI ===
        JPanel pannello = new JPanel();
        pannello.setLayout(new GridLayout(5, 4, 10, 10)); // 5 righe, 4 colonne, spaziatura 10px
        pannello.setBackground(new Color(40, 40, 40));

        // Array con i testi dei pulsanti
        String[] pulsanti = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        // Creo i pulsanti e li aggiungo al pannello
        for (String testo : pulsanti) {
            JButton btn = new JButton(testo);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(60, 60, 60));
            btn.setFocusPainted(false);
            btn.addActionListener(this); // collega i pulsanti all'evento click
            pannello.add(btn);
        }

        // Aggiungo il pannello al centro della finestra
        add(pannello, BorderLayout.CENTER);

        // Rendo visibile la finestra
        setVisible(true);
    }

    // === GESTIONE DEI CLICK ===
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand(); // testo del pulsante premuto

        // Se è un numero o un punto
        if ((comando.charAt(0) >= '0' && comando.charAt(0) <= '9') || comando.equals(".")) {
            display.setText(display.getText() + comando);
        }
        // Se è un'operazione
        else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            try {
                numero1 = Double.parseDouble(display.getText());
                operatore = comando.charAt(0);
                display.setText("");
            } catch (Exception ex) {
                display.setText("Errore");
            }
        }
        // Se è "=" → eseguo il calcolo
        else if (comando.equals("=")) {
            try {
                numero2 = Double.parseDouble(display.getText());
                switch (operatore) {
                    case '+': risultato = numero1 + numero2; break;
                    case '-': risultato = numero1 - numero2; break;
                    case '*': risultato = numero1 * numero2; break;
                    case '/': 
                        if (numero2 == 0) {
                            display.setText("Div/0");
                            return;
                        }
                        risultato = numero1 / numero2;
                        break;
                }
                display.setText(String.valueOf(risultato));
            } catch (Exception ex) {
                display.setText("Errore");
            }
        }
        // Se è "C" → cancella tutto
        else if (comando.equals("C")) {
            display.setText("");
            numero1 = numero2 = risultato = 0;
        }
    }

    // === MAIN ===
    public static void main(String[] args) {
        // Imposta un look moderno (Nimbus) se disponibile
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Se Nimbus non è disponibile, usa quello di default
        }

        // Avvia la calcolatrice
        new CalcolatriceGrafica();
    }
}

