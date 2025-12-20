Keno Lottery Game Simulation
A fully interactive casino-style Keno game built with JavaFX, featuring programmatic UI construction, event-driven gameplay, and dynamic animations. This single-player lottery simulation offers an engaging user experience with real-time number drawing, win calculations, and comprehensive game state management.
Overview
This application recreates the popular casino and state lottery game Keno with a polished JavaFX interface. Players select their lucky numbers (or let the system choose randomly), watch as 20 numbers are drawn with animated reveals, and track their winnings across multiple drawing rounds. The project demonstrates advanced GUI programming without relying on FXML or Scene Builder, showcasing pure JavaFX component architecture.
Key Features
Interactive Gameplay

Dynamic Bet Card: 8x10 GridPane with 80 clickable nodes for number selection
Flexible Betting: Choose 1, 4, 8, or 10 spot games with varying payout structures
Multi-Drawing Support: Play the same bet card across 1-4 consecutive drawings
Quick Pick Feature: Automated random number selection for players who prefer instant play
Real-Time Drawing Animation: 20 numbers revealed one-by-one with pause transitions for dramatic effect
Live Win Tracking: Instant feedback on matched numbers and winnings per drawing, plus cumulative totals

User Interface Design

Welcome Screen: Dedicated intro scene with menu navigation and game rules access
Game Play Screen: Comprehensive layout with bet card, spot selection, drawing controls, and results display
Menu System:

Rules display for new players
Odds calculator showing payout tables
"New Look" feature for dynamic UI theme changes
Exit functionality


Visual Feedback: Selected numbers highlighted, matched numbers indicated, disabled states for proper game flow
Error Prevention: Input validation preventing duplicate selections, over-selection, and illegal game states

Game Flow Management

State-Driven Architecture: Programmatic control ensuring players follow proper betting sequence
Controlled Progression: "Continue" prompts and pause transitions maintain game pacing
Dynamic Component States: UI elements enable/disable based on game phase (setup vs. active drawing)
Reset Functionality: Clean slate between rounds with option to start new games or exit

Technologies Used
Frontend

JavaFX for complete GUI framework
Programmatic UI construction (no FXML/Scene Builder)
GridPane for structured bet card layout
Scene-based navigation for multi-screen experience
PauseTransition for animation timing

Backend

Java for game logic and state management
Random number generation with duplicate prevention
Payout calculation engine based on NC State Lottery odds
Maven for build management

Testing

JUnit 5 with 25+ comprehensive unit tests
Test coverage for game logic, payout calculations, and number matching

Game Rules
How to Play

Choose Your Spots: Select how many numbers to play (1, 4, 8, or 10 spots)
Pick Your Numbers: Click numbers 1-80 on the bet card, or use Quick Pick for random selection
Set Drawings: Decide how many times to play your bet card (1-4 drawings)
Watch & Win: Twenty numbers are drawn randomly; match your numbers to win based on payout table
Play Again: After all drawings complete, start a new game or cash out

Payout Structure
Based on North Carolina State Lottery Keno payouts:

1 Spot: Match 1 number
4 Spots: Match 2-4 numbers
8 Spots: Match 4-8 numbers
10 Spots: Match 5-10 numbers

Higher matches yield exponentially better payouts (specific odds displayed in-game via Rules menu)
Technical Highlights
Frontend Development Skills

Programmatic JavaFX component creation and styling
Event-driven architecture with comprehensive event handlers
Multi-scene application with smooth transitions
GridPane mastery for complex grid-based layouts
Dynamic UI state management (enable/disable controls based on game phase)
User experience design with visual feedback and pacing controls
Animation integration for engaging gameplay
Input validation and error prevention
Menu system implementation with multiple navigation paths

Software Engineering Practices

Maven project structure and dependency management
Test-driven development with JUnit 5
Separation of concerns (UI vs. game logic)
State management for complex application flow
Comprehensive input validation

Project Structure
Project2Fall2025/
├── src/main/java/         
│   ├── JavaFXTemplate.java      # Main application entry
│   ├── GameLogic.java           # Keno game rules and calculations
│   ├── BetCard.java             # Player number selections
│   └── [additional classes]     
├── src/test/java/         
│   └── [JUnit test files]       # 25+ unit tests
└── pom.xml                      # Maven configuration
Installation & Running
Prerequisites

Java JDK 11 or higher
Maven 3.6.3 or higher

Run the Application
bashmvn clean javafx:run
Run Tests
bashmvn test
Design Philosophy
This project prioritizes user experience through careful attention to:

Pacing: Animations and pause transitions prevent information overload
Clarity: Visual states clearly indicate what's clickable, selected, and matched
Guidance: Prompts and disabled states guide players through proper game sequence
Feedback: Immediate visual response to all user interactions
Accessibility: Clear labeling, logical layout, and intuitive controls

The programmatic UI construction (vs. FXML) demonstrates deep understanding of JavaFX component hierarchy, layout managers, and event handling.
UI/UX Features
Welcome Screen

Clean introduction with branding
Menu access to rules and odds before playing
Single "Start" button for clear call-to-action

Game Screen

Organized layout with distinct sections for bet card, controls, and results
Visual differentiation between enabled/disabled states
Matched numbers highlighted during drawing reveals
Running totals for transparency
"New Look" feature demonstrating CSS-like dynamic styling changes

User Flow Controls

Spot selection → Number picking → Drawing count → Start drawing → View results → Continue/New game
Input validation at each step prevents errors
Clear prompts guide players to next action

Development Approach
This project was built with a design-first methodology:

Wireframing: Sketched complete UI before coding
Component Mapping: Identified all JavaFX components and layout managers
Event Flow Planning: Mapped all user interactions and state changes
Programmatic Thinking: Planned enable/disable logic and validation checks
Iterative Testing: Built game logic with unit tests before GUI integration

Academic Context
Developed as part of CS 342 (Software Design) at the University of Illinois at Chicago, demonstrating:

Advanced JavaFX GUI programming
Event-driven software architecture
User interface and user experience design principles
Test-driven development practices
Maven build system proficiency

Future Enhancements

Multiple bet cards per game
Betting amounts and bankroll management
Historical statistics and analytics dashboard
Multiplayer support with leaderboards
Accessibility features (screen reader support, keyboard navigation)
Mobile-responsive design

License
Academic project for University of Illinois at Chicago.
