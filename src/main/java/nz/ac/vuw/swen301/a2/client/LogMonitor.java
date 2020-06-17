package nz.ac.vuw.swen301.a2.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.util.Objects;

public class LogMonitor {

    public static void main (String[] args) {
        JFrame frame = logMonitorCreateGUI();
        frame.repaint();
    }

    private static JFrame logMonitorCreateGUI() {
        String[] columnNames = {"time", "level", "logger", "thread", "message"};
        String[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);


        JFrame frame = new JFrame("LOG Monitor");
        int x = 800;
        int y = 600;
        frame.setSize(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        frame.setVisible(true);
        panel.setLayout(null);

        JLabel labelLevel = new JLabel("min level:");
        labelLevel.setBounds(20, 20, 80, 25);
        panel.add(labelLevel);

        String[] levels = {"ALL","TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL", "OFF"};
        JComboBox<String> comboBox = new JComboBox<>(levels);
        comboBox.setBounds(90, 20, 80, 25);
        panel.add(comboBox);


        JLabel labelLimit = new JLabel("limit:");
        labelLimit.setBounds(190, 20, 80, 25);
        panel.add(labelLimit);

        JTextField textLimit = new JTextField(20);
        textLimit.setBounds(230, 20, 80, 25);
        textLimit.setText("100");
        textLimit.setHorizontalAlignment(0);
        panel.add(textLimit);

        JButton fetchData = new JButton("FETCH DATA");
        fetchData.setBounds(330,20,150,25);
        fetchData.addActionListener(actionEvent -> {
            String level = comboBox.getSelectedItem().toString();
            String limit = textLimit.getText();
            JSONArray jsonArray = getData(level,limit);
            if(jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String timestamp = jsonObject.get("timestamp").toString();
                    String jsonLevel = jsonObject.get("level").toString();
                    String logger = jsonObject.get("logger").toString();
                    String message = jsonObject.get("message").toString();
                    String thread = jsonObject.get("thread").toString();

                    model.addRow(new String[]{timestamp,jsonLevel,logger,message,thread});
                }
            }
        });
        panel.add(fetchData);

        JTable dataTable = new JTable(model);
        dataTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(20, 60, x - 40, y);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        return frame;

    }

    public static JSONArray getData(String level, String limit) {
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/resthome4logs/logs").setParameter("level", level).setParameter("limit", limit).build();
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(uri);
            HttpResponse response = httpClient.execute(get);
            String responseData = EntityUtils.toString(response.getEntity());
            if(response.getStatusLine().getStatusCode()==200) {
                return new JSONArray(responseData);
            }
            return null;
        } catch (Exception e ){

        }
        return null;
    }
}
