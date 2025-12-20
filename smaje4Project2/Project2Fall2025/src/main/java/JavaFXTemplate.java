import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import java.util.*;

/**
 * Author: Salwa Majeed
 * CS 342 Project 2
 * JavaFX Keno Game Application
 * A lottery-style game where players select numbers and try to match randomly drawn numbers.
 * Supports 1, 4, 8, or 10 spot selections with multiple drawing rounds.
 */
public class JavaFXTemplate extends Application {

    // Stage and Scene references
    private Stage primaryStage;
    private Scene welcomeScene;  // Initial welcome screen
    private Scene gameScene;     // Main game screen
    
    // Game state variables - track current game progress
    private int selectedSpots = 0;       
    private int selectedDrawings = 0;     
    private Set<Integer> playerNumbers = new HashSet<>();  // Player's chosen numbers
    private List<Integer> drawnNumbers = new ArrayList<>(); // Numbers drawn in current round
    private int totalWinnings = 0;        // Cumulative winnings across all drawings
    private int currentDrawing = 0;       // Current drawing number
    private boolean isDrawing = false;    // Flag to prevent changes during drawing
    private boolean alternateTheme = false; // Toggle between light and dark themes
    
    private Button[][] betCardButtons;    
    private Label statusLabel;            
    private Label totalWinningsLabel;     
    private Label drawingLabel;           
    private TextArea resultsArea;         
    private Button startDrawingButton;    
    private Button continueButton;      
    private Button newGameButton;       
    private ComboBox<Integer> spotsComboBox;   
    private ComboBox<Integer> drawingsComboBox; 
    private Button randomPickButton;     
    private VBox drawnNumbersBox;       
    private GridPane betCardGrid;        
    
    /**
     * Application entry point
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * JavaFX start method - initializes and displays the welcome screen
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        
        // Create welcome screen
        this.welcomeScene = createWelcomeScene();
        
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Welcome to Keno");
        primaryStage.show();    
    }
    
    /**
     * Creates the welcome/splash screen with game information
     * @return Scene containing the welcome screen layout
     */
    private Scene createWelcomeScene() {
        BorderPane root = new BorderPane();
        
        // Add menu bar at top
        MenuBar menuBar = createWelcomeMenuBar();
        root.setTop(menuBar);
        
        // Main content container with card-style design
        VBox centerContent = new VBox(30);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(60));
        centerContent.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                              "-fx-background-radius: 20; " +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 20, 0, 0, 8);");
        centerContent.setMaxWidth(650);
        centerContent.setMaxHeight(550);
        
        // Welcome title with shadow effect
        Label welcomeLabel = new Label("Welcome to Keno!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 56));
        welcomeLabel.setTextFill(Color.web("#16213e"));
        welcomeLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        
        // Subtitle with gradient background
        Label subtitleLabel = new Label("🎲 Test Your Luck! 🎰");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        subtitleLabel.setTextFill(Color.WHITE);
        subtitleLabel.setPadding(new Insets(15, 30, 15, 30));
        subtitleLabel.setStyle("-fx-background-color: linear-gradient(to right, #667eea, #764ba2); " +
                              "-fx-background-radius: 15; " +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        
        // Information box explaining game rules
        VBox infoBox = new VBox(12);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setPadding(new Insets(20));
        infoBox.setMaxWidth(500);
        infoBox.setStyle("-fx-background-color: #f8f9fa; " +
                        "-fx-background-radius: 12; " +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 12;");
        
        // Game rule information labels
        Label info1 = new Label("✓ Pick 1, 4, 8, or 10 numbers");
        info1.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        info1.setTextFill(Color.web("#2c3e50"));
        
        Label info2 = new Label("✓ Match drawn numbers to win");
        info2.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        info2.setTextFill(Color.web("#2c3e50"));
        
        Label info3 = new Label("✓ Play up to 4 drawings");
        info3.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        info3.setTextFill(Color.web("#2c3e50"));
        
        infoBox.getChildren().addAll(info1, info2, info3);
        
        // Start button with enhanced styling and hover effects
        Button startButton = new Button("▶  START PLAYING");
        startButton.setPrefSize(280, 60);
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        startButton.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); " +
                           "-fx-text-fill: white; " +
                           "-fx-background-radius: 15; " +
                           "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 5);");
        
        // Button hover effect - enlarges and changes color
        startButton.setOnMouseEntered(e -> 
            startButton.setStyle("-fx-background-color: linear-gradient(to bottom, #229954, #1e8449); " +
                               "-fx-text-fill: white; " +
                               "-fx-background-radius: 15; " +
                               "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 12, 0, 0, 6); " +
                               "-fx-cursor: hand; " +
                               "-fx-scale-x: 1.05; " +
                               "-fx-scale-y: 1.05;")
        );
        startButton.setOnMouseExited(e -> 
            startButton.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); " +
                               "-fx-text-fill: white; " +
                               "-fx-background-radius: 15; " +
                               "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 5);")
        );
        
        // Start button action - transitions to game screen
        startButton.setOnAction(e -> {
            gameScene = createGameScene();
            primaryStage.setScene(gameScene);
            primaryStage.setTitle("Keno Game");
        });
        
        // Assemble center content
        centerContent.getChildren().addAll(welcomeLabel, subtitleLabel, infoBox, startButton);
        
        // Wrapper to center the content card on screen
        StackPane wrapper = new StackPane();
        wrapper.getChildren().add(centerContent);
        wrapper.setPadding(new Insets(40));
        root.setCenter(wrapper);
        
        // Set gradient background matching game screen
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #1a1a2e, #16213e, #0f3460);");
        
        return new Scene(root, 900, 750);
    }
    
    /**
     * Creates the main game screen with bet card and controls
     * @return Scene containing the game interface
     */
    private Scene createGameScene() {
        BorderPane root = new BorderPane();
        
        // Add menu bar
        MenuBar menuBar = createGameMenuBar();
        root.setTop(menuBar);
        
        // Main horizontal layout - left (bet card) and right (results)
        HBox mainLayout = new HBox(25);
        mainLayout.setPadding(new Insets(25, 30, 25, 30));
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom right, #1a1a2e, #16213e, #0f3460);");
        
        // ===== LEFT SIDE: Bet Card and Controls =====
        VBox leftSide = new VBox(20);
        leftSide.setPrefWidth(520);
        leftSide.setPadding(new Insets(20));
        leftSide.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                         "-fx-background-radius: 15; " +
                         "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 5);");
        
        // Section title
        Label betCardTitle = new Label("🎲 YOUR BET CARD");
        betCardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        betCardTitle.setTextFill(Color.web("#16213e"));
        betCardTitle.setAlignment(Pos.CENTER);
        betCardTitle.setMaxWidth(Double.MAX_VALUE);
        
        // Selection controls container
        VBox selectionBox = new VBox(12);
        selectionBox.setPadding(new Insets(15));
        selectionBox.setStyle("-fx-background-color: #f8f9fa; " +
                             "-fx-background-radius: 10; " +
                             "-fx-border-color: #e9ecef; " +
                             "-fx-border-radius: 10; " +
                             "-fx-border-width: 2;");
        
        // Spots selection dropdown (1, 4, 8, or 10)
        HBox spotsRow = new HBox(10);
        spotsRow.setAlignment(Pos.CENTER_LEFT);
        Label spotsLabel = new Label("Select Spots:");
        spotsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        spotsLabel.setTextFill(Color.web("#2c3e50"));
        spotsComboBox = new ComboBox<>();
        spotsComboBox.getItems().addAll(1, 4, 8, 10);
        spotsComboBox.setPromptText("Choose...");
        spotsComboBox.setPrefWidth(100);
        spotsComboBox.setStyle("-fx-font-size: 13px;");
        spotsComboBox.setOnAction(e -> onSpotsSelected());
        spotsRow.getChildren().addAll(spotsLabel, spotsComboBox);
        
        // Drawings selection dropdown (1-4)
        HBox drawingsRow = new HBox(10);
        drawingsRow.setAlignment(Pos.CENTER_LEFT);
        Label drawingsLabel = new Label("Drawings:");
        drawingsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        drawingsLabel.setTextFill(Color.web("#2c3e50"));
        drawingsComboBox = new ComboBox<>();
        drawingsComboBox.getItems().addAll(1, 2, 3, 4);
        drawingsComboBox.setPromptText("Choose...");
        drawingsComboBox.setPrefWidth(100);
        drawingsComboBox.setStyle("-fx-font-size: 13px;");
        drawingsComboBox.setOnAction(e -> selectedDrawings = drawingsComboBox.getValue());
        drawingsRow.getChildren().addAll(drawingsLabel, drawingsComboBox);
        
        // Random pick button - auto-selects required number of spots
        randomPickButton = new Button("🎰 Random Pick");
        randomPickButton.setDisable(true);  // Disabled until spots are selected
        randomPickButton.setPrefHeight(35);
        randomPickButton.setMaxWidth(Double.MAX_VALUE);
        randomPickButton.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        randomPickButton.setStyle("-fx-background-color: #9b59b6; " +
                                 "-fx-text-fill: white; " +
                                 "-fx-background-radius: 8; " +
                                 "-fx-cursor: hand;");
        randomPickButton.setOnMouseEntered(e -> {
            if (!randomPickButton.isDisabled()) {
                randomPickButton.setStyle("-fx-background-color: #8e44ad; " +
                                         "-fx-text-fill: white; " +
                                         "-fx-background-radius: 8; " +
                                         "-fx-cursor: hand;");
            }
        });
        randomPickButton.setOnMouseExited(e -> {
            if (!randomPickButton.isDisabled()) {
                randomPickButton.setStyle("-fx-background-color: #9b59b6; " +
                                         "-fx-text-fill: white; " +
                                         "-fx-background-radius: 8; " +
                                         "-fx-cursor: hand;");
            }
        });
        randomPickButton.setOnAction(e -> randomlySelectNumbers());
        
        selectionBox.getChildren().addAll(spotsRow, drawingsRow, randomPickButton);
        
        // Bet card grid - 8x10 grid of numbers 1-80
        betCardGrid = new GridPane();
        betCardGrid.setHgap(6);
        betCardGrid.setVgap(6);
        betCardGrid.setAlignment(Pos.CENTER);
        betCardGrid.setPadding(new Insets(15));
        betCardGrid.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea, #764ba2); " +
                            "-fx-background-radius: 12; " +
                            "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);");
        
        // Create 80 number buttons arranged in 8 rows x 10 columns
        betCardButtons = new Button[8][10];
        int num = 1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                Button btn = new Button(String.valueOf(num));
                btn.setPrefSize(48, 48);
                btn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                btn.setStyle("-fx-background-color: white; " +
                           "-fx-border-color: #ddd; " +
                           "-fx-border-width: 1; " +
                           "-fx-background-radius: 8; " +
                           "-fx-border-radius: 8;");
                btn.setDisable(true);  // Initially disabled until spots are selected
                
                final int number = num;
                btn.setOnAction(e -> onNumberSelected(number, btn));
                
                // Hover effect for interactive feedback
                btn.setOnMouseEntered(e -> {
                    if (!btn.isDisabled() && !playerNumbers.contains(number)) {
                        btn.setStyle("-fx-background-color: #e8f4f8; " +
                                   "-fx-border-color: #3498db; " +
                                   "-fx-border-width: 2; " +
                                   "-fx-background-radius: 8; " +
                                   "-fx-border-radius: 8; " +
                                   "-fx-cursor: hand;");
                    }
                });
                btn.setOnMouseExited(e -> {
                    if (!btn.isDisabled() && !playerNumbers.contains(number)) {
                        btn.setStyle("-fx-background-color: white; " +
                                   "-fx-border-color: #ddd; " +
                                   "-fx-border-width: 1; " +
                                   "-fx-background-radius: 8; " +
                                   "-fx-border-radius: 8;");
                    }
                });
                
                betCardButtons[row][col] = btn;
                betCardGrid.add(btn, col, row);
                num++;
            }
        }
        
        // Status label - provides instructions and feedback to user
        statusLabel = new Label("Select the number of spots to play");
        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        statusLabel.setWrapText(true);
        statusLabel.setTextFill(Color.web("#2c3e50"));
        statusLabel.setPadding(new Insets(10));
        statusLabel.setStyle("-fx-background-color: #fff3cd; " +
                            "-fx-background-radius: 8; " +
                            "-fx-border-color: #ffc107; " +
                            "-fx-border-radius: 8; " +
                            "-fx-border-width: 2;");
        statusLabel.setMaxWidth(Double.MAX_VALUE);
        statusLabel.setAlignment(Pos.CENTER);
        
        // Control buttons for game flow
        startDrawingButton = new Button("▶ Start Drawing");
        startDrawingButton.setDisable(true);
        startDrawingButton.setPrefSize(160, 45);
        startDrawingButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        startDrawingButton.setStyle("-fx-background-color: #27ae60; " +
                                   "-fx-text-fill: white; " +
                                   "-fx-background-radius: 10; " +
                                   "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        startDrawingButton.setOnMouseEntered(e -> {
            if (!startDrawingButton.isDisabled()) {
                startDrawingButton.setStyle("-fx-background-color: #229954; " +
                                           "-fx-text-fill: white; " +
                                           "-fx-background-radius: 10; " +
                                           "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                           "-fx-cursor: hand;");
            }
        });
        startDrawingButton.setOnMouseExited(e -> {
            if (!startDrawingButton.isDisabled()) {
                startDrawingButton.setStyle("-fx-background-color: #27ae60; " +
                                           "-fx-text-fill: white; " +
                                           "-fx-background-radius: 10; " +
                                           "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
            }
        });
        startDrawingButton.setOnAction(e -> startDrawing());
        
        // Continue button - shown between multiple drawings
        continueButton = new Button("➤ Continue");
        continueButton.setDisable(true);
        continueButton.setVisible(false);  // Hidden until needed
        continueButton.setPrefSize(160, 45);
        continueButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        continueButton.setStyle("-fx-background-color: #3498db; " +
                               "-fx-text-fill: white; " +
                               "-fx-background-radius: 10; " +
                               "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        continueButton.setOnMouseEntered(e -> {
            if (!continueButton.isDisabled()) {
                continueButton.setStyle("-fx-background-color: #2980b9; " +
                                       "-fx-text-fill: white; " +
                                       "-fx-background-radius: 10; " +
                                       "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                       "-fx-cursor: hand;");
            }
        });
        continueButton.setOnMouseExited(e -> {
            if (!continueButton.isDisabled()) {
                continueButton.setStyle("-fx-background-color: #3498db; " +
                                       "-fx-text-fill: white; " +
                                       "-fx-background-radius: 10; " +
                                       "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
            }
        });
        continueButton.setOnAction(e -> continueToNextDrawing());
        
        // New game button - resets everything for a fresh start
        newGameButton = new Button("🔄 New Game");
        newGameButton.setDisable(true);
        newGameButton.setVisible(false);  // Hidden until game completes
        newGameButton.setPrefSize(160, 45);
        newGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        newGameButton.setStyle("-fx-background-color: #e67e22; " +
                              "-fx-text-fill: white; " +
                              "-fx-background-radius: 10; " +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        newGameButton.setOnMouseEntered(e -> {
            if (!newGameButton.isDisabled()) {
                newGameButton.setStyle("-fx-background-color: #d35400; " +
                                      "-fx-text-fill: white; " +
                                      "-fx-background-radius: 10; " +
                                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                      "-fx-cursor: hand;");
            }
        });
        newGameButton.setOnMouseExited(e -> {
            if (!newGameButton.isDisabled()) {
                newGameButton.setStyle("-fx-background-color: #e67e22; " +
                                      "-fx-text-fill: white; " +
                                      "-fx-background-radius: 10; " +
                                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);");
            }
        });
        newGameButton.setOnAction(e -> resetGame());
        
        // Button container
        HBox buttonBox = new HBox(12);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startDrawingButton, continueButton, newGameButton);
        
        // Assemble left side
        leftSide.getChildren().addAll(betCardTitle, selectionBox, betCardGrid, statusLabel, buttonBox);
        
        // ===== RIGHT SIDE: Drawing Display and Results =====
        VBox rightSide = new VBox(18);
        rightSide.setPrefWidth(340);
        rightSide.setPadding(new Insets(20));
        rightSide.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                          "-fx-background-radius: 15; " +
                          "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 5);");
        
        // Drawing info section- shows current drawing .and total winnings
        VBox drawingInfoBox = new VBox(8);
        drawingInfoBox.setPadding(new Insets(12));
        drawingInfoBox.setStyle("-fx-background-color: linear-gradient(to right, #f093fb, #f5576c); " +
                               "-fx-background-radius: 10;");
        
        drawingLabel = new Label("Drawing: 0/0");
        drawingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        drawingLabel.setTextFill(Color.WHITE);
        
        totalWinningsLabel = new Label("Total Winnings: $0");
        totalWinningsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        totalWinningsLabel.setTextFill(Color.web("#ffd700"));  // Gold color for winnings
        totalWinningsLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 3, 0, 0, 1);");
        
        drawingInfoBox.getChildren().addAll(drawingLabel, totalWinningsLabel);
        
        // Drawn numbers display section
        Label drawnLabel = new Label("🎱 Drawn Numbers");
        drawnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        drawnLabel.setTextFill(Color.web("#2c3e50"));
        
        // Container for dynacally added drawn number labels
        drawnNumbersBox = new VBox(5);
        drawnNumbersBox.setPrefHeight(180);
        drawnNumbersBox.setPadding(new Insets(10));
        drawnNumbersBox.setStyle("-fx-background-color: #f8f9fa; " +
                                "-fx-border-color: #dee2e6; " +
                                "-fx-border-width: 2; " +
                                "-fx-border-radius: 8; " +
                                "-fx-background-radius: 8;");
        
        // Scrollable container for drawn numbers
        ScrollPane drawnScroll = new ScrollPane(drawnNumbersBox);
        drawnScroll.setPrefHeight(180);
        drawnScroll.setFitToWidth(true);
        drawnScroll.setStyle("-fx-background: transparent; " +
                            "-fx-background-color: transparent;");
        
        // Results section - detailed match information
        Label resultsLabel = new Label("📊 Results");
        resultsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        resultsLabel.setTextFill(Color.web("#2c3e50"));
        
        // Text area for displaying detailed results
        resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setPrefHeight(180);
        resultsArea.setWrapText(true);
        resultsArea.setFont(Font.font("Courier New", 12));
        resultsArea.setStyle("-fx-control-inner-background: #f8f9fa; " +
                           "-fx-border-color: #dee2e6; " +
                           "-fx-border-width: 2; " +
                           "-fx-border-radius: 8; " +
                           "-fx-background-radius: 8;");
        
        // Assemble right side
        rightSide.getChildren().addAll(drawingInfoBox, drawnLabel, drawnScroll, resultsLabel, resultsArea);
        
        // Combine left and right sides
        mainLayout.getChildren().addAll(leftSide, rightSide);
        root.setCenter(mainLayout);
        
        return new Scene(root, 950, 750);
    }
    
    /**
     * Handles the spots selection event
     * Enables bet card buttons and prompts user to select numbers
     */
    private void onSpotsSelected() {
        if (isDrawing) return;  // Prevent changes during drawing
        
        selectedSpots = spotsComboBox.getValue();
        playerNumbers.clear();
        
        // Enable all bet card buttons for selection
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                betCardButtons[i][j].setDisable(false);
                betCardButtons[i][j].setStyle("-fx-background-color: white; " +
                                             "-fx-border-color: #ddd; " +
                                             "-fx-border-width: 1; " +
                                             "-fx-background-radius: 8; " +
                                             "-fx-border-radius: 8;");
            }
        }
        
        randomPickButton.setDisable(false);
        statusLabel.setText("Select " + selectedSpots + " number(s) from the bet card");
    }
    
    /**
     * Handles individual number selection on the bet card
     * @param number The number that was clicked (1-80)
     * @param btn The button that was clicked
     */
    private void onNumberSelected(int number, Button btn) {
        if (isDrawing) return;  // Prevent changes during drawing
        
        // Toggle selection: if already selected, deselect it
        if (playerNumbers.contains(number)) {
            playerNumbers.remove(number);
            btn.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #ddd; " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 8; " +
                        "-fx-border-radius: 8;");
        } else {
            // Only allow selection up to the chosen spot limit
            if (playerNumbers.size() < selectedSpots) {
                playerNumbers.add(number);
                // Highlight selected button with green gradient
                btn.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); " +
                           "-fx-text-fill: white; " +
                           "-fx-border-color: #1e8449; " +
                           "-fx-border-width: 2; " +
                           "-fx-background-radius: 8; " +
                           "-fx-border-radius: 8; " +
                           "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2);");
            } else {
                // Warn user they've reached selection limit
                statusLabel.setText("You can only select " + selectedSpots + " numbers!");
            }
        }
        
        checkReadyToStart();
    }
    
    /**
     * Randomly selects the required number of spots for the player
     * Useful for quick play or when user doesn't want to manually pick
     */
    private void randomlySelectNumbers() {
        if (isDrawing || selectedSpots == 0) return;
        
        playerNumbers.clear();
        Random rand = new Random();
        
        // Reset all button styles to default
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                betCardButtons[i][j].setStyle("-fx-background-color: white; " +
                                             "-fx-border-color: #ddd; " +
                                             "-fx-border-width: 1; " +
                                             "-fx-background-radius: 8; " +
                                             "-fx-border-radius: 8;");
            }
        }
        
        // Randomly select unique numbers until enough
        while (playerNumbers.size() < selectedSpots) {
            int num = rand.nextInt(80) + 1;  // Random number from 1-80
            playerNumbers.add(num);  // Set automatically handles duplicates
        }
        
        // Highlight all randomly selected numbers
        for (int num : playerNumbers) {
            // Convert number to grid coordinates (0-indexed)
            int index = num - 1;
            int row = index / 10;
            int col = index % 10;
            betCardButtons[row][col].setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); " +
                                             "-fx-text-fill: white; " +
                                             "-fx-border-color: #1e8449; " +
                                             "-fx-border-width: 2; " +
                                             "-fx-background-radius: 8; " +
                                             "-fx-border-radius: 8; " +
                                             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2);");
        }
        
        checkReadyToStart();
    }
    
    /**
     * Checks if all requirements are met to start the drawing
     * Enables start button when ready and updates status label
     */
    private void checkReadyToStart() {
        if (playerNumbers.size() == selectedSpots && selectedDrawings > 0) {
            startDrawingButton.setDisable(false);
            statusLabel.setText("Ready to start! Click 'Start Drawing' button.");
        } else if (playerNumbers.size() == selectedSpots) {
            statusLabel.setText("Select number of drawings to continue");
        } else {
            startDrawingButton.setDisable(true);
            statusLabel.setText("Selected: " + playerNumbers.size() + "/" + selectedSpots);
        }
    }
    
    /**
     * Initiates the drawing process
     * Locks all controls and begins the first drawing
     */
    private void startDrawing() {
        // Validate selections
        if (playerNumbers.size() != selectedSpots || selectedDrawings == 0) {
            statusLabel.setText("Please complete your selections!");
            return;
        }
        
        // Lock game state to prevent changes during drawing
        isDrawing = true;
        currentDrawing = 1;
        startDrawingButton.setDisable(true);
        spotsComboBox.setDisable(true);
        drawingsComboBox.setDisable(true);
        randomPickButton.setDisable(true);
        
        // Disable all bet card buttons - no more changes allowed
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                betCardButtons[i][j].setDisable(true);
            }
        }
        
        performDrawing();
    }
    
    /**
     * Performs a single drawing round
     * Randomly selects 20 numbers from 1-80 and animates their display
     */
    private void performDrawing() {
        drawingLabel.setText("Drawing: " + currentDrawing + "/" + selectedDrawings);
        statusLabel.setText("Drawing in progress...");
        drawnNumbers.clear();
        drawnNumbersBox.getChildren().clear();
        
        // Generate 20 random unique numbers from 1-80 (standard Keno rules)
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            allNumbers.add(i);
        }
        Collections.shuffle(allNumbers);  // Randomize the order
        drawnNumbers = allNumbers.subList(0, 20);  // Take first 20
        
        // Animate the drawing of numbers one by one
        SequentialTransition sequence = new SequentialTransition();
        
        for (int i = 0; i < 20; i++) {
            final int index = i;
            // Pause 300ms between each number reveal
            PauseTransition pause = new PauseTransition(Duration.millis(300));
            pause.setOnFinished(e -> {
                int num = drawnNumbers.get(index);
                Label numLabel = new Label(String.valueOf(num));
                numLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                numLabel.setPadding(new Insets(8));
                numLabel.setMaxWidth(Double.MAX_VALUE);
                numLabel.setAlignment(Pos.CENTER);
                
                // Highlight matched numbers (player selected this number)
                if (playerNumbers.contains(num)) {
                    numLabel.setStyle("-fx-background-color: linear-gradient(to right, #f7b733, #fc4a1a); " +
                                    "-fx-text-fill: white; " +
                                    "-fx-border-color: #d35400; " +
                                    "-fx-border-width: 2; " +
                                    "-fx-background-radius: 6; " +
                                    "-fx-border-radius: 6; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 5, 0, 0, 2);");
                } else {
                    // Non-matched numbers shown in gray
                    numLabel.setStyle("-fx-background-color: #ecf0f1; " +
                                    "-fx-text-fill: #34495e; " +
                                    "-fx-border-color: #bdc3c7; " +
                                    "-fx-border-width: 1; " +
                                    "-fx-background-radius: 6; " +
                                    "-fx-border-radius: 6;");
                }
                
                drawnNumbersBox.getChildren().add(numLabel);
            });
            sequence.getChildren().add(pause);
        }
        
        // After all numbers are drawn, display results
        sequence.setOnFinished(e -> displayResults());
        sequence.play();
    }
    
    /**
     * Calculates and displays the results of the current drawing
     * Shows matches, calculates winnings, and determines next action
     */
    private void displayResults() {
        // Calculate matches using set intersection
        Set<Integer> matches = new HashSet<>(playerNumbers);
        matches.retainAll(drawnNumbers);  // Keep only numbers that appear in both sets
        
        int matchCount = matches.size();
        int winAmount = calculateWinnings(selectedSpots, matchCount);
        totalWinnings += winAmount;
        
        // Build formatted results string
        StringBuilder result = new StringBuilder();
        result.append("Drawing ").append(currentDrawing).append(" Results:\n");
        result.append("------------------------\n");
        result.append("Your numbers: ").append(playerNumbers).append("\n");
        result.append("Matches: ").append(matches).append("\n");
        result.append("Match count: ").append(matchCount).append("\n");
        result.append("Winnings: $").append(winAmount).append("\n\n");
        
        resultsArea.appendText(result.toString());
        totalWinningsLabel.setText("Total Winnings: $" + totalWinnings);
        
        // Determine next action based on remaining drawings
        if (currentDrawing < selectedDrawings) {
            // More drawings remain - show continue button
            statusLabel.setText("Drawing complete! Click 'Continue' for next drawing.");
            continueButton.setDisable(false);
            continueButton.setVisible(true);
        } else {
            // All drawings complete - show new game button
            statusLabel.setText("All drawings complete! Start a new game or exit.");
            newGameButton.setDisable(false);
            newGameButton.setVisible(true);
            isDrawing = false;  // Unlock game state
        }
    }
    
    /**
     * Advances to the next drawing round
     * Called when user clicks Continue button between drawings
     */
    private void continueToNextDrawing() {
        currentDrawing++;
        continueButton.setDisable(true);
        continueButton.setVisible(false);
        performDrawing();
    }
    
    /**
     * Resets all game state variables and UI elements for a new game
     * Returns the interface to initial state
     */
    private void resetGame() {
        // Clear all game data
        playerNumbers.clear();
        drawnNumbers.clear();
        selectedSpots = 0;
        selectedDrawings = 0;
        currentDrawing = 0;
        isDrawing = false;
        
        // Clear UI displays
        drawnNumbersBox.getChildren().clear();
        resultsArea.clear();
        drawingLabel.setText("Drawing: 0/0");
        totalWinningsLabel.setText("Total Winnings: $0");
        statusLabel.setText("Select the number of spots to play");
        
        // Reset dropdowns
        spotsComboBox.setValue(null);
        drawingsComboBox.setValue(null);
        spotsComboBox.setDisable(false);
        drawingsComboBox.setDisable(false);
        
        // Reset control buttons
        startDrawingButton.setDisable(true);
        continueButton.setDisable(true);
        continueButton.setVisible(false);
        newGameButton.setDisable(true);
        newGameButton.setVisible(false);
        randomPickButton.setDisable(true);
        
        // Reset all bet card buttons to initial disabled state
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                betCardButtons[i][j].setDisable(true);
                betCardButtons[i][j].setStyle("-fx-background-color: white; " +
                                             "-fx-border-color: #ddd; " +
                                             "-fx-border-width: 1; " +
                                             "-fx-background-radius: 8; " +
                                             "-fx-border-radius: 8;");
            }
        }
    }
    
    /**
     * Calculates winnings based on NC Lottery Keno payout table
     * Different spot selections have different payout structures
     * 
     * @param spots Number of spots selected (1, 4, 8, or 10)
     * @param matches Number of matches achieved
     * @return Dollar amount won
     */
    private int calculateWinnings(int spots, int matches) {
        switch (spots) {
            case 1:
                // 1-spot game: only pays if you match your single number
                return (matches == 1) ? 2 : 0;
                
            case 4:
                // 4-spot game: pays for 2, 3, or 4 matches
                if (matches == 4) return 75;
                if (matches == 3) return 5;
                if (matches == 2) return 1;
                return 0;
                
            case 8:
                // 8-spot game: pays for 4-8 matches
                if (matches == 8) return 10000;
                if (matches == 7) return 750;
                if (matches == 6) return 50;
                if (matches == 5) return 12;
                if (matches == 4) return 2;
                return 0;
                
            case 10:
                // 10-spot game: pays for 5-10 matches
                // Special rule: matching 0 out of 10 also wins!
                if (matches == 10) return 100000;
                if (matches == 9) return 4500;
                if (matches == 8) return 300;
                if (matches == 7) return 40;
                if (matches == 6) return 15;
                if (matches == 5) return 2;
                if (matches == 0) return 5;  // Bonus: hitting none of the numbers!
                return 0;
                
            default:
                return 0;
        }
    }
    
    /**
     * Applies alternate theme (dark mode) to the game scene
     * Switches between light and dark color schemes
     * 
     * @param root The root BorderPane of the game scene
     * @param alternate True for dark theme, false for light theme
     */
    private void applyTheme(BorderPane root, boolean alternate) {
        HBox mainLayout = (HBox) root.getCenter();
        VBox leftSide = (VBox) mainLayout.getChildren().get(0);
        VBox rightSide = (VBox) mainLayout.getChildren().get(1);
        
        if (alternate) {
            // ===== DARK THEME =====
            mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #34495e, #2c3e50);");
            
            // Card styling
            leftSide.setStyle("-fx-background-color: rgba(44, 62, 80, 0.95); " +
                             "-fx-background-radius: 15; " +
                             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 15, 0, 0, 5);");
            
            rightSide.setStyle("-fx-background-color: rgba(44, 62, 80, 0.95); " +
                              "-fx-background-radius: 15; " +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 15, 0, 0, 5);");
            
            // Background
            Label betCardTitle = (Label) leftSide.getChildren().get(0);
            betCardTitle.setTextFill(Color.web("#ecf0f1"));
            
            VBox selectionBox = (VBox) leftSide.getChildren().get(1);
            selectionBox.setStyle("-fx-background-color: #34495e; " +
                                 "-fx-background-radius: 10; " +
                                 "-fx-border-color: #546e7a; " +
                                 "-fx-border-radius: 10; " +
                                 "-fx-border-width: 2;");
            
            HBox spotsRow = (HBox) selectionBox.getChildren().get(0);
            ((Label) spotsRow.getChildren().get(0)).setTextFill(Color.web("#ecf0f1"));
            
            HBox drawingsRow = (HBox) selectionBox.getChildren().get(1);
            ((Label) drawingsRow.getChildren().get(0)).setTextFill(Color.web("#ecf0f1"));
            
            // Bet card grid
            betCardGrid.setStyle("-fx-background-color: linear-gradient(to bottom, #546e7a, #455a64); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);");
            
            // Status label
            statusLabel.setTextFill(Color.web("#ecf0f1"));
            statusLabel.setStyle("-fx-background-color: #455a64; " +
                                "-fx-background-radius: 8; " +
                                "-fx-border-color: #546e7a; " +
                                "-fx-border-radius: 8; " +
                                "-fx-border-width: 2;");
            
            // Drawing info box
            VBox drawingInfoBox = (VBox) rightSide.getChildren().get(0);
            drawingInfoBox.setStyle("-fx-background-color: linear-gradient(to right, #34495e, #2c3e50); " +
                                   "-fx-background-radius: 10;");
            
            // Label colors
            Label drawnLabel = (Label) rightSide.getChildren().get(1);
            drawnLabel.setTextFill(Color.web("#ecf0f1"));
            
            Label resultsLabel = (Label) rightSide.getChildren().get(3);
            resultsLabel.setTextFill(Color.web("#ecf0f1"));
            
            // Drawn numbers display
            drawnNumbersBox.setStyle("-fx-background-color: #34495e; " +
                                    "-fx-border-color: #546e7a; " +
                                    "-fx-border-width: 2; " +
                                    "-fx-border-radius: 8; " +
                                    "-fx-background-radius: 8;");
            
            // Results text area
            resultsArea.setStyle("-fx-control-inner-background: #34495e; " +
                               "-fx-text-fill: #ecf0f1; " +
                               "-fx-border-color: #546e7a; " +
                               "-fx-border-width: 2; " +
                               "-fx-border-radius: 8; " +
                               "-fx-background-radius: 8;");
            
        } else {
            // ===== LIGHT THEME =====
            mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom right, #1a1a2e, #16213e, #0f3460);");
            
            // Card styling
            leftSide.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                             "-fx-background-radius: 15; " +
                             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 5);");
            
            rightSide.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); " +
                              "-fx-background-radius: 15; " +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 5);");
            
            // Text colors
            Label betCardTitle = (Label) leftSide.getChildren().get(0);
            betCardTitle.setTextFill(Color.web("#16213e"));
            
            VBox selectionBox = (VBox) leftSide.getChildren().get(1);
            selectionBox.setStyle("-fx-background-color: #f8f9fa; " +
                                 "-fx-background-radius: 10; " +
                                 "-fx-border-color: #e9ecef; " +
                                 "-fx-border-radius: 10; " +
                                 "-fx-border-width: 2;");
            
            HBox spotsRow = (HBox) selectionBox.getChildren().get(0);
            ((Label) spotsRow.getChildren().get(0)).setTextFill(Color.web("#2c3e50"));
            
            HBox drawingsRow = (HBox) selectionBox.getChildren().get(1);
            ((Label) drawingsRow.getChildren().get(0)).setTextFill(Color.web("#2c3e50"));
            
            // Bet card grid with gradient
            betCardGrid.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea, #764ba2); " +
                                "-fx-background-radius: 12; " +
                                "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);");
            
            // Status label
            statusLabel.setTextFill(Color.web("#2c3e50"));
            statusLabel.setStyle("-fx-background-color: #fff3cd; " +
                                "-fx-background-radius: 8; " +
                                "-fx-border-color: #ffc107; " +
                                "-fx-border-radius: 8; " +
                                "-fx-border-width: 2;");
            
            // Light drawing info box
            VBox drawingInfoBox = (VBox) rightSide.getChildren().get(0);
            drawingInfoBox.setStyle("-fx-background-color: linear-gradient(to right, #f093fb, #f5576c); " +
                                   "-fx-background-radius: 10;");
            
            // Label colors for light theme
            Label drawnLabel = (Label) rightSide.getChildren().get(1);
            drawnLabel.setTextFill(Color.web("#2c3e50"));
            
            Label resultsLabel = (Label) rightSide.getChildren().get(3);
            resultsLabel.setTextFill(Color.web("#2c3e50"));
            
            // Light drawn numbers display
            drawnNumbersBox.setStyle("-fx-background-color: #f8f9fa; " +
                                    "-fx-border-color: #dee2e6; " +
                                    "-fx-border-width: 2; " +
                                    "-fx-border-radius: 8; " +
                                    "-fx-background-radius: 8;");
            
            // Light results text area
            resultsArea.setStyle("-fx-control-inner-background: #f8f9fa; " +
                               "-fx-text-fill: #2c3e50; " +
                               "-fx-border-color: #dee2e6; " +
                               "-fx-border-width: 2; " +
                               "-fx-border-radius: 8; " +
                               "-fx-background-radius: 8;");
        }
    }
    
    /**
     * Creates the menu bar for the welcome screen
     * @return MenuBar with Rules, Odds, and Exit options
     */
    private MenuBar createWelcomeMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Menu");
        
        MenuItem rulesItem = new MenuItem("Rules");
        rulesItem.setOnAction(e -> showRules());
        
        MenuItem oddsItem = new MenuItem("Odds of Winning");
        oddsItem.setOnAction(e -> showOdds());
        
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        
        menu.getItems().addAll(rulesItem, oddsItem, exitItem);
        menuBar.getMenus().add(menu);
        
        return menuBar;
    }
    
    /**
     * Creates the menu bar for the game screen
     * Includes additional "New Look" option to toggle theme
     * @return MenuBar with Rules, Odds, New Look, and Exit options
     */
    private MenuBar createGameMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Menu");
        
        MenuItem rulesItem = new MenuItem("Rules");
        rulesItem.setOnAction(e -> showRules());
        
        MenuItem oddsItem = new MenuItem("Odds of Winning");
        oddsItem.setOnAction(e -> showOdds());
        
        // Theme toggle option
        MenuItem newLookItem = new MenuItem("New Look");
        newLookItem.setOnAction(e -> {
            alternateTheme = !alternateTheme;
            applyTheme((BorderPane) gameScene.getRoot(), alternateTheme);
        });
        
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        
        menu.getItems().addAll(rulesItem, oddsItem, newLookItem, exitItem);
        menuBar.getMenus().add(menu);
        
        return menuBar;
    }
    
    /**
     * Displays a dialog with game rules and instructions
     */
    private void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Rules");
        alert.setHeaderText("How to Play Keno");
        alert.setContentText(
            "1. Choose how many spots to play (1, 4, 8, or 10)\n" +
            "2. Select your numbers from 1-80\n" +
            "3. Choose how many drawings to play (1-4)\n" +
            "4. 20 numbers will be drawn randomly\n" +
            "5. Match your numbers to win!\n\n" +
            "The more numbers you match, the more you win!"
        );
        alert.showAndWait();
    }
    
    /**
     * Displays a dialog with odds information for different spot selections
     */
    private void showOdds() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Odds of Winning");
        alert.setHeaderText("Keno Odds");
        alert.setContentText(
            "Odds vary based on spots played:\n\n" +
            "1 Spot: Match 1 number\n" +
            "4 Spots: Match 2-4 numbers\n" +
            "8 Spots: Match 4-8 numbers\n" +
            "10 Spots: Match 5-10 numbers\n" +
            "  (Special: 0 matches also wins!)\n\n" +
            "Visit NC Lottery website for detailed payout information."
        );
        alert.showAndWait();
    }
}