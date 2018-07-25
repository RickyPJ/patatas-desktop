/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ricardo
 */
public class MiRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row == 0) {
            this.setOpaque(true);
            this.setBackground(new Color(0, 150, 255));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 10) {
            this.setOpaque(true);
            this.setBackground(new Color(250, 250, 150));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 12) {
            this.setOpaque(true);
            this.setBackground(Color.RED);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 20) {
            this.setOpaque(true);
            this.setBackground(new Color(0, 150, 0));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 28) {
            this.setOpaque(true);
            this.setBackground(new Color(200, 150, 0));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 33) {
            this.setOpaque(true);
            this.setBackground(new Color(0, 75, 0));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 37) {
            this.setOpaque(true);
            this.setBackground(new Color(103, 80, 70));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 41) {
            this.setOpaque(true);
            this.setBackground(new Color(255, 75, 0));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 43) {
            this.setOpaque(true);
            this.setBackground(Color.ORANGE);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 46) {
            this.setOpaque(true);
            this.setBackground(Color.GRAY);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 52) {
            this.setOpaque(true);
            this.setBackground(Color.GRAY);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 58) {
            this.setOpaque(true);
            this.setBackground(Color.GRAY);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 62) {
            this.setOpaque(true);
            this.setBackground(Color.GRAY);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (row == 64) {
            this.setOpaque(true);
            this.setBackground(Color.GRAY);
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Serif", Font.BOLD, 12));
            this.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (column == 3 && row < 66) {
            this.setOpaque(true);
            this.setBackground(Color.LIGHT_GRAY);
            this.setForeground(Color.BLACK);
        } else {
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return this;
    }
}
