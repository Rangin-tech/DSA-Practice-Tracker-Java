import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DSATrackerGUI extends JFrame implements ActionListener {

    JTextField problemField;
    JTextField topicField;
    JTextField difficultyField;

    JTextArea displayArea;

    JButton addButton;
    JButton showButton;
    JButton topicButton;
    JButton difficultyButton;
    JButton weakTopicButton;

    ArrayList<Problem> problems = new ArrayList<>();

    DSATrackerGUI() {

        setTitle("DSA Practice Tracker");
        setSize(500,500);
        setLayout(new FlowLayout());

        problemField = new JTextField(20);
        topicField = new JTextField(20);
        difficultyField = new JTextField(20);

        add(new JLabel("Problem Name"));
        add(problemField);

        add(new JLabel("Topic"));
        add(topicField);

        add(new JLabel("Difficulty"));
        add(difficultyField);

        addButton = new JButton("Add Problem");
        showButton = new JButton("Show Problems");
        topicButton = new JButton("Topic Analysis");
        difficultyButton = new JButton("Difficulty Analysis");
        weakTopicButton = new JButton("Weak Topic");

        add(addButton);
        add(showButton);
        add(topicButton);
        add(difficultyButton);
        add(weakTopicButton);

        displayArea = new JTextArea(15,40);
        displayArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(displayArea);
        add(scroll);

        addButton.addActionListener(this);
        showButton.addActionListener(this);
        topicButton.addActionListener(this);
        difficultyButton.addActionListener(this);
        weakTopicButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Add problem
        if(e.getSource()==addButton){

            String name = problemField.getText();
            String topic = topicField.getText();
            String difficulty = difficultyField.getText();

            Problem p = new Problem(name,topic,difficulty);
            problems.add(p);

            displayArea.setText("Problem Added Successfully\n");

            problemField.setText("");
            topicField.setText("");
            difficultyField.setText("");
        }

        // Show all problems
        if(e.getSource()==showButton){

            displayArea.setText("");

            for(Problem p : problems){

                displayArea.append(p.toString()+"\n");
            }
        }

        // Topic analysis
        if(e.getSource()==topicButton){

            HashMap<String,Integer> map = new HashMap<>();

            for(Problem p : problems){

                map.put(p.topic, map.getOrDefault(p.topic,0)+1);
            }

            displayArea.setText("Topic Analysis\n");

            for(String topic : map.keySet()){

                displayArea.append(topic+" : "+map.get(topic)+"\n");
            }
        }

        // Difficulty analysis
        if(e.getSource()==difficultyButton){

            HashMap<String,Integer> map = new HashMap<>();

            for(Problem p : problems){

                String diff = p.difficulty.toLowerCase();

                map.put(diff, map.getOrDefault(diff,0)+1);
            }

            displayArea.setText("Difficulty Analysis\n");

            for(String d : map.keySet()){

                displayArea.append(d + " : " + map.get(d) + "\n");
            }
        }

        // Weak topic detection
        if(e.getSource()==weakTopicButton){

            HashMap<String,Integer> map = new HashMap<>();

            for(Problem p : problems){

                map.put(p.topic, map.getOrDefault(p.topic,0)+1);
            }

            String weakTopic = "";
            int min = Integer.MAX_VALUE;

            for(String topic : map.keySet()){

                if(map.get(topic) < min){

                    min = map.get(topic);
                    weakTopic = topic;
                }
            }

            displayArea.setText("Weak Topic Detected\n");
            displayArea.append(weakTopic + " (only " + min + " problems solved)");
        }
    }

    public static void main(String[] args) {

        new DSATrackerGUI();
    }
}