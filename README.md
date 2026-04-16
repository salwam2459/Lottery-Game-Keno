# Keno Lottery Game

## What Is This?
- A casino-style **Keno lottery game** you can play on your computer
- Built using **JavaFX** (a Java tool for making graphical apps)
- Single-player game where you pick numbers and watch them get drawn
- Tracks your winnings across multiple rounds

---

## How to Play
- **Step 1 – Choose your spots:** Decide how many numbers you want to play (1, 4, 8, or 10)
- **Step 2 – Pick your numbers:** Click up to 80 numbers on the board, or hit **Quick Pick** to let the game choose for you
- **Step 3 – Set your drawings:** Choose how many rounds to play (1–4)
- **Step 4 – Watch the draw:** 20 numbers are revealed one at a time — matched numbers light up!
- **Step 5 – See your winnings:** View your results and choose to play again or quit

---

## Key Features

### Gameplay
- Clickable number grid (8×10, numbers 1–80)
- Choose between 1-spot, 4-spot, 8-spot, or 10-spot games
- Play the same numbers across up to 4 rounds
- Quick Pick for instant random number selection
- Numbers are drawn one by one with a dramatic animation
- Win totals update live after each round

### Menus & Screens
- **Welcome screen** with a clear Start button
- **Rules screen** explaining how to play
- **Odds calculator** showing payout tables
- **"New Look" button** that changes the app's color theme
- Exit option available from the menu

### Smart Game Flow
- Buttons automatically enable/disable based on where you are in the game
- Prevents you from picking too many numbers or making invalid selections
- Prompts guide you step by step so you never get confused

---

## Payout Structure
*Based on North Carolina State Lottery Keno odds:*

- **1 Spot:** Win by matching 1 number
- **4 Spots:** Win by matching 2, 3, or 4 numbers
- **8 Spots:** Win by matching 4–8 numbers
- **10 Spots:** Win by matching 5–10 numbers
- The more numbers you match, the bigger the payout — full odds shown in-game

---

## Tech Used

### Frontend (What You See)
- **JavaFX** — builds the entire visual interface in code (no drag-and-drop tools)
- Grid layout for the number board
- Animations for the number drawing sequence
- Multiple screens (welcome → game → results)

### Backend (What Runs the Game)
- **Java** — handles all the game rules and math
- Random number generator (no repeats)
- Payout calculator based on real lottery odds
- **Maven** — manages the project build

### Testing
- **JUnit 5** — 25+ automated tests
- Tests cover game logic, payout math, and number matching

---

## How to Run It

### What You Need First
- Java JDK 11 or newer
- Maven 3.6.3 or newer

### Start the Game
```bash
mvn clean javafx:run
```

### Run the Tests
```bash
mvn test
```

---

## Project Structure
```
Project2Fall2025/
├── src/main/java/
│   ├── JavaFXTemplate.java   ← Launches the app
│   ├── GameLogic.java        ← Game rules & payout math
│   ├── BetCard.java          ← Stores your number picks
│   └── [other files]
├── src/test/java/
│   └── [25+ test files]
└── pom.xml                   ← Maven config
```

---

## Design Choices
- **Animations slow things down on purpose** — so you can follow along and enjoy the reveal
- **Buttons gray out** when you can't use them — no guessing what to do next
- **Everything is coded by hand** — no drag-and-drop UI builder, showing deep JavaFX knowledge
- **Guided flow** — the game walks you through each step clearly

---

## About This Project
- Built for **CS 342 (Software Design)** at the University of Illinois at Chicago
- Demonstrates: JavaFX, event-driven design, UI/UX principles, unit testing, and Maven

---

## Future Features
- Multiple bet cards per game
- Real money/bankroll simulation
- Stats dashboard with game history
- Multiplayer with leaderboards
- Keyboard navigation & screen reader support

---

*Academic project — University of Illinois at Chicago*
