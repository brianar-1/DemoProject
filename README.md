# KENO Game Simulation

Watch Demo Video:

## Overview
This project is a JavaFX implementation of the popular lottery game **Keno**. The goal of this project is to demonstrate proficiency in **GUI development using JavaFX** and **event driven programming** while simulating a real time lottery game experience.

---

## How the Game Works
Keno is a gambling game offered in many casinos and state lotteries. Players wager by selecting a set of numbers (called "spots") from 1 to 80. After all selections are made, 20 numbers are randomly drawn. Players win by matching their numbers with the drawn numbers.  

### Key Concepts
- **Bet Card:** A grid of numbers (1–80) for player selection.
- **Number of Spots:** Players can choose 1, 4, 8, or 10 numbers to play.
- **Drawings:** Each round consists of 20 random numbers drawn with no duplicates. Players can play 1–4 drawings per bet card.
- **Gameplay:** The program allows a single player to fill a bet card manually or automatically, select the number of drawings, and see the results of each drawing in real time.

---

## Features
1. **Welcome Screen**
   - Displays a menu bar with options:
     - Game rules
     - Odds of winning
     - Exit the game
   - Button to start playing the game.

2. **Game Play Screen**
   - Displays the bet card using an **8x10 GridPane**.
   - Menu option to change the "look" of the GUI (colors, fonts, etc.).
   - Players can:
     - Select the number of spots (1, 4, 8, 10).
     - Choose numbers manually or let the program pick randomly.
     - Select 1 - 4 drawings to play.

3. **Gameplay**
   - Prevents invalid inputs (duplicate numbers, exceeding selected spots).
   - Provides a user friendly flow with pause transitions and prompts to guide the player.
