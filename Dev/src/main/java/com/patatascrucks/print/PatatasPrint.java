// 224 x 298
// 567 x 756
package com.patatascrucks.print;

import javax.swing.*;
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;

public class PatatasPrint extends JFrame implements Printable {

    JTextArea fact;
    String hoja;
    boolean printed = false;

    public PatatasPrint(String f) {
        this.setUndecorated(true);
        this.setVisible(true);
        this.setSize(432, 900);
        this.setExtendedState(MAXIMIZED_VERT);
        this.setResizable(false);
        hoja = f;
        fact = new JTextArea(hoja);
        fact.setEditable(false);
        fact.setFont(new Font("Courier New", Font.BOLD, 9));
        add(fact, BorderLayout.CENTER);
        double width = 4 * 72d;
        double height = 50 * 72d;
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat page = new PageFormat();
        Paper paper = new Paper();
        paper.setSize(width, height);
        paper.setImageableArea(3d, 0d, width, height);
        page.setPaper(paper);
        job.setPrintable(PatatasPrint.this, page);
        //boolean ok = job.printDialog();
        if (!job.isCancelled()) {
            try {
                job.print();
                Thread.sleep(500);
                this.dispose();
                printed = true;
            } catch (PrinterException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Error al Imprimir!\n" + ex.getMessage());
                dispose();
            }
        } else {
            printed = false;
        }

        addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        PatatasPrint.this.dispose();
                    }
                }
        );
        
        //this.setVisible(true);
    }

    public boolean wasPrinted() {
        return printed;
    }

    @Override
    public int print(Graphics g, PageFormat format, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(format.getImageableX(), format.getImageableY());

        this.printAll(g);

        return PAGE_EXISTS;
    }
}
