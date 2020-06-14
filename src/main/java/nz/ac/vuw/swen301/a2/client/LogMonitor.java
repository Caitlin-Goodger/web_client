package nz.ac.vuw.swen301.a2.client;

import javax.swing.*;

public class LogMonitor {
    private String logServiceURL = "http://localhost:8080/resthome4logs/logs";

    public static void main (String[] args) {
        JFrame frame = logMonitorCreateGUI();
        frame.repaint();
    }

    private static JFrame logMonitorCreateGUI() {

    }
}
