# Compilation Instructions

## Steps to Compile:
1. **Install IntelliJ IDEA Community** if not already installed.
2. **Open Project in IntelliJ** via `File > Open...`.
3. **Load Maven Dependencies**: IntelliJ autoloads dependencies. If not, click the Maven tool window and refresh.
4. **Set Correct JDK**: Ensure the project uses a compatible JDK Version 23.
5. **Build Project**: Select `Build > Build Project`.
6. **Run Application**: Click the green Run button or press `Shift + F10`.
7. **Play the Game**: The game window will open. Enjoy!

## Dependencies:
- **Maven** for dependencies
- **JavaFX** for UI

---

# Main Menu

## Purpose:
The `MainMenu` class serves as the starting point of the game, offering players two options: to start the game or quit. It manages the main menu interface, displaying buttons for both actions and handling their functionality. The "Start" button initiates the game, while the "Quit" button closes the application, providing an intuitive entry and exit point for the player.

## Benefits:
- Enhances the user experience with an aesthetically pleasing interface, custom background, and styled buttons with hover effects.
- Promotes modularity by separating the UI logic of the main menu from the rest of the game, making the code more maintainable and scalable.
- Ensures a smooth and organized flow from the menu to the gameplay.

---

# Pause Menu

## Purpose:
The `PauseMenu` class manages the pause functionality in the game. It allows the player to pause and unpause the game by pressing the "P" key. When paused, the game is dimmed with a semi-transparent background, and a message instructing the player to press "P" again to unpause is displayed. The class interacts with the game's timeline, stopping it when paused and resuming it when unpaused.

## Benefits:
- Enhances the player experience by providing a simple and intuitive way to pause the game.
- Ensures the game interface remains clear during pauses with a dimmed background and pause message.
- Allows seamless pausing and unpausing, improving gameplay flow without interrupting the user's experience.
- Modular design, making it easy to maintain and integrate into the game.

---

# Level One Overview

Level One is the first stage of the game, where the player begins their journey. The objective is to defeat a set number of enemy planes while managing the player's health. The level features dynamic gameplay with enemies spawned at random intervals based on a defined probability. The player must destroy enough enemies to reach a kill count threshold to advance to the next level.

Key Features:
- Visual background, health tracking, and a kill counter that updates as enemies are defeated.
- If the player’s health reaches zero, the game ends.
- Once the player accumulates a set number of kills (7 in this case), they will automatically transition to **Level Two**.

## Progression to the Next Level:
To progress to the next level, the player needs to achieve the kill target—destroying 7 enemy planes. The game continuously tracks the number of kills, updating the kill count on the screen. Upon reaching the kill target, the game triggers a smooth transition to **Level Two**. If the player is defeated before reaching the target, the game ends, and the player must restart.

---

# Level Two Overview

In **Level Two**, the player faces off against a powerful boss, which is the only enemy in this stage. The boss has a unique mechanic where it randomly activates a shield, making it invulnerable for a brief period. The player's goal is to defeat the boss, but they must wait for the shield to deactivate before dealing damage. The level concludes when the player either defeats the boss or their plane is destroyed.

## Progression and Win Condition:
- Once the player defeats the boss, a "You Win" image is displayed, marking the completion of Level Two.
- There is no subsequent level, so defeating the boss is the final milestone of the game.
- If the player's plane is destroyed before defeating the boss, the game ends.

This level is designed as a challenging boss fight, with the random shield mechanic adding difficulty.
