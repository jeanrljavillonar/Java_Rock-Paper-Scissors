import java.awt.*; // Imports basic GUI classes (layouts, containers, etc.)
import java.awt.event.ActionEvent; // Imports ActionEvent class for button events
import java.awt.event.ActionListener; // Imports ActionListener interface for handling actions
import java.util.Random; // Imports Random class for generating random choices
import javax.swing.*; // Imports Swing GUI components

public class RockPaperScissors extends JFrame implements ActionListener { // Main class, extends JFrame for window, implements ActionListener for button clicks

    private final JButton rockBtn; // Button for "Rock"
    private final JButton paperBtn, scissorsBtn; // Buttons for "Paper" and "Scissors"
    private final JLabel  resultLabel, scoreLabel; // Labels for showing result and score
    private int userScore = 0, computerScore = 0; // Variables to keep track of scores
    
    public RockPaperScissors() { // Constructor to set up the GUI
        // Set up the GUI components
        setTitle("Rock Paper Scissors"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app when window closes
        setLayout(new BorderLayout()); // Use BorderLayout for arranging components
        
        // Create buttons
        rockBtn = new JButton("Rock"); // Create "Rock" button
        paperBtn = new JButton("Paper"); // Create "Paper" button
        scissorsBtn = new JButton("Scissors"); // Create "Scissors" button
        
        // Create panel for buttons
        JPanel buttonPanel = new JPanel(); // Create a panel to hold buttons
        buttonPanel.add(rockBtn); // Add "Rock" button to panel
        buttonPanel.add(paperBtn); // Add "Paper" button to panel
        buttonPanel.add(scissorsBtn); // Add "Scissors" button to panel
        
        // Create result label
        resultLabel = new JLabel("Choose your move!", SwingConstants.CENTER); // Label for result, centered
        scoreLabel = new JLabel("Score: You 0 - 0 Computer", SwingConstants.CENTER); // Label for score, centered
        
        // Add components to frame
        add(buttonPanel, BorderLayout.CENTER); // Add button panel to center of window
        add(resultLabel, BorderLayout.NORTH); // Add result label to top of window
        add(scoreLabel, BorderLayout.SOUTH); // Add score label to bottom of window
        
        pack(); // Adjust window size to fit components
        setSize(400, 200); // Set window size
        setVisible(true); // Show the window
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { // Handles button clicks
        String userChoice = ""; // Stores user's choice
        if (e.getSource() == rockBtn) userChoice = "rock"; // If "Rock" clicked
        if (e.getSource() == paperBtn) userChoice = "paper"; // If "Paper" clicked
        if (e.getSource() == scissorsBtn) userChoice = "scissors"; // If "Scissors" clicked
        
        // Computer's random choice
        String[] options = {"rock", "paper", "scissors"}; // Possible choices
        String computerChoice = options[new Random().nextInt(3)]; // Randomly pick one
        
        // Determine winner
        String result = determineWinner(userChoice, computerChoice); // Get result string
        
        // Update score
        if (result.contains("Win")) userScore++; // If user wins, increment user score
        if (result.contains("Lose")) computerScore++; // If user loses, increment computer score
        
        // Update UI
        resultLabel.setText("You chose " + userChoice + ", Computer chose " + 
                           computerChoice + ". " + result); // Show choices and result
        scoreLabel.setText("Score: You " + userScore + " - " + computerScore + " Computer"); // Show updated score
    }
    
    private String determineWinner(String user, String computer) { // Decides who wins
        if (user.equals(computer)) return "It's a Tie!"; // If same, it's a tie
        
        // Use switch to decide winner
        return switch (user) {
            case "rock" -> computer.equals("scissors") ? "You Win!" : "You Lose!";
            case "paper" -> computer.equals("rock") ? "You Win!" : "You Lose!";
            case "scissors" -> computer.equals("paper") ? "You Win!" : "You Lose!";
            default -> "Invalid choice!"; // Should not happen
        };
    }
    
    public static void main(String[] args) { // Main method to start the app
        SwingUtilities.invokeLater(() -> { // Run GUI code on Event Dispatch Thread
            RockPaperScissors game = new RockPaperScissors(); // Create game window
            game.rockBtn.addActionListener(game); // Add listener to "Rock" button
            game.paperBtn.addActionListener(game); // Add listener to "Paper" button
            game.scissorsBtn.addActionListener(game); // Add listener to "Scissors" button
        });
    }
}
