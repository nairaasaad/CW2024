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

# Features Implemented

1. **MainMenu Class**: 
   The `MainMenu` class has been successfully implemented as the starting point of the game. It provides two primary options for the player: to start the game or quit. The interface includes styled buttons with hover effects, creating an intuitive and user-friendly entry point for the game.

2. **Shield Visibility for Boss**:
   The visibility of the boss's shield in Level Two was fixed. The boss can now activate a shield that makes it invulnerable for a short period, enhancing the gameplay by adding unpredictability and challenge to the fight.

3. **Enemy Plane Overlap Prevention**:
   A new method was added to ensure enemy planes do not overlap during their spawn. This method improves the visual clarity and gameplay experience by preventing the screen from becoming cluttered, making it easier for the player to navigate.

4. **Horizontal Movement**:
   The player character can now move both horizontally and vertically. Previously, movement was limited to vertical only, but this update adds more flexibility, allowing the player to better navigate the battlefield.

5. **Pause Menu**:
   The `PauseMenu` class was implemented successfully. When the "P" key is pressed, the game pauses, and the screen is dimmed with a semi-transparent overlay. Pressing "P" again resumes the game, providing an intuitive way to pause and unpause the game during play.

6. **Kill Count in Level One**:  
    A `killCount` feature was added to Level One, which tracks the number of enemy planes the player has destroyed.

---

# Features Not Implemented

1. **Retry Level Button**:
   A retry button was attempted but could not be implemented successfully due to a recurring error that caused the game to crash when the player lost. The issue stemmed from attempting to reset the game state, and despite multiple debugging attempts, it remained unresolved within the available time frame.

2. **New Level (Combination of Level One and Level Two)**:
   I attempted to create a new level that combined elements from Level One and Level Two, but I could not complete this feature on time. The new level was intended to introduce a transition between the two stages, adding complexity and variety to the gameplay. Unfortunately, I could not resolve the technical challenges in time to implement this properly.

3. **Bullet Spam Prevention**:
   A feature to prevent the player from spamming bullets was also attempted but did not function as expected. Despite attempts to limit the firing rate of bullets, the game did not restrict the player from firing continuously. The implementation was unsuccessful, and further adjustments will be needed to achieve the desired result.

# New Java Classes

1. **MainMenu Class**
   - **Purpose**: The `MainMenu` class serves as the starting point for the game. It provides the user interface for the main menu, allowing the player to either start a new game or quit the application. The class manages the display of buttons for these actions and handles the functionality associated with each button.
   - **Location in Code**: This class is located in the `UI` package, specifically in `com/example/demo/UI`.

2. **PauseMenu Class**
   - **Purpose**: The `PauseMenu` class manages the pause functionality in the game. It allows the player to pause and unpause the game by pressing the "P" key. The game’s state is temporarily frozen, and the screen displays a semi-transparent overlay with a message instructing the player to press "P" again to resume. This class controls the interaction with the game's timeline and pause behavior.
   - **Location in Code**: This class is located in the `UI` package, specifically in `com/example/demo/UI`.

# Modified Java Classes

## Boss Class

### **Overview**
The `Boss` class represents a powerful boss fighter plane in the game. Significant updates were made to enhance the gameplay experience, improve visual effects, and increase the challenge level for the player. The changes include adjustments to the boss's image size, movement behavior, projectile firing rate, and the introduction of a shield mechanic with a visual representation.

### **Key Changes:**

**Package Declaration**:  
  The class has been moved to the `com.example.demo.Actor` package for better organization. 
  
#### 1. **Image Height Adjustment**
   - **Original Image Height**: `300`
   - **Updated Image Height**: `70`
   - **Purpose**: The boss plane's image was resized to fit better within the game environment, creating a more balanced appearance.

#### 2. **Increased Fire Rate**
   - **Original Fire Rate**: `0.04`
   - **Updated Fire Rate**: `0.05`
   - **Purpose**: Increased the boss’s fire rate to make it more aggressive and challenging for the player.

#### 3. **Introduction of Shield Mechanic**
   - **New Feature**: A shield was added, which makes the boss temporarily invulnerable to damage.
   - **Implementation**: A `ShieldImage` object was introduced, and shield activation/deactivation was added to the boss’s behavior. The shield is visually represented and can deactivate randomly during its active period.
   - **Purpose**: To add a visual cue for the player when the boss is invulnerable, enhancing the game’s difficulty and strategy.

#### 4. **Movement Pattern Adjustments**
   - The boss’s vertical movement pattern was updated to ensure it stays within the screen’s bounds, preventing it from moving off-screen. The shield's position now also tracks the boss's vertical movement.

#### 5. **Health Adjustment**
   - **Original Health**: `100`
   - **Updated Health**: `20`
   - **Purpose**: The boss’s health was adjusted to account for the new shield mechanic, ensuring a balanced difficulty curve.

### **Additional Changes**
- The projectile firing system was slightly modified to accommodate the new image and layout of the boss.
- The game’s difficulty was fine-tuned with random shield activation and a higher rate of fire for the boss.


## GameOverImage Class

**Package Declaration**:  
The class has been moved to the `com.example.demo.UI` package for better organization.


#### 1. **Image Height Adjustment**
   - **Original Image Height**: `300`  
   - **Updated Image Height**: `70`  
   - **Purpose**: The boss plane's image was resized to fit better within the game environment, creating a more balanced appearance.


#### 2. **Increased Fire Rate**
   - **Original Fire Rate**: `0.04`  
   - **Updated Fire Rate**: `0.05`  
   - **Purpose**: Increased the boss’s fire rate to make it more aggressive and challenging for the player.


#### 3. **Introduction of Shield Mechanic**
   - **New Feature**: A shield was added, which makes the boss temporarily invulnerable to damage.  
   - **Implementation**: A `ShieldImage` object was introduced, and shield activation/deactivation was added to the boss’s behavior. The shield is visually represented and can deactivate randomly during its active period.  
   - **Purpose**: To add a visual cue for the player when the boss is invulnerable, enhancing the game’s difficulty and strategy.


#### 4. **Movement Pattern Adjustments**
   - The boss’s vertical movement pattern was updated to ensure it stays within the screen’s bounds, preventing it from moving off-screen. The shield's position now also tracks the boss's vertical movement.


#### 5. **Health Adjustment**
   - **Original Health**: `100`  
   - **Updated Health**: `20`  
   - **Purpose**: The boss’s health was adjusted to account for the new shield mechanic, ensuring a balanced difficulty curve.


### **Additional Changes**
- The projectile firing system was slightly modified to accommodate the new image and layout of the boss.
- The game’s difficulty was fine-tuned with random shield activation and a higher rate of fire for the boss.

# Level Parent Class

## Overview
The `Level` class serves as the parent class for game levels, handling common game mechanics such as enemy and player interactions, level transitions, and game state management. This class has undergone several updates to improve the game's structure and ensure better handling of multiple levels and transitions, along with the addition of new features such as level difficulty scaling and an improved game loop.

## Key Changes

### **Package Declaration**
- **Updated Package**: `com.example.demo.Level`

### **1. Added Level Difficulty Scaling**
- **New Feature**: The `Level` class now includes a mechanism for dynamically adjusting the level's difficulty based on player progress or game conditions.
- **Implementation**: 
  - A `difficulty` attribute was introduced.
  - Difficulty is adjusted after certain milestones or events, affecting enemy behavior, health, and fire rates.
- **Purpose**: To ensure that each level becomes progressively harder, providing an engaging experience for the player.

### **2. Level Transition Management**
- **New Feature**: Improved handling of level transitions.
- **Implementation**: 
  - A `transitionToNextLevel()` method was added to facilitate smooth transitions between levels.
  - Level reset logic was introduced to prepare the game environment and player state for the next stage.
- **Purpose**: To ensure that players experience seamless transitions between levels, maintaining immersion and continuity in gameplay.

### **3. Enhanced Game State Management**
- **New Feature**: Improved management of the game state during level changes.
- **Implementation**: 
  - The `pauseGame()` and `resumeGame()` methods were integrated for managing the game’s pause state across multiple levels.
  - Game state is now preserved when switching between levels, preventing the loss of progress.
- **Purpose**: To provide better control over the game's state, including pausing and resuming functionality during level transitions.

### **4. Common Enemy Handling**
- **New Feature**: The `Level` class now includes logic for handling common enemy behaviors shared across all levels.
- **Implementation**: 
  - Methods for spawning enemies and managing their movement, shooting, and health were moved to the `Level` class.
  - These common behaviors can be customized or overridden in child classes to adapt to specific level requirements.
- **Purpose**: To reduce code duplication and centralize common enemy logic for better maintainability and scalability.

### **5. Player and Enemy Interaction**
- **Improvement**: The player-enemy interaction logic was refined to ensure smoother combat mechanics across levels.
- **Implementation**: 
  - Collision detection and damage application were enhanced.
  - Interaction events, such as enemy death or player damage, are now handled more efficiently.
- **Purpose**: To improve the responsiveness and accuracy of interactions between the player and enemies, making the gameplay feel more fluid.

### **6. New Level Specific Features**
- **New Feature**: The ability to add level-specific mechanics or events.
- **Implementation**: 
  - A `levelSpecificEvents()` method was added, allowing child classes to define custom events, such as special enemies or objectives, for specific levels.
  - Custom behaviors can now be injected into the `Level` class via inheritance.
- **Purpose**: To allow for more flexibility in level design while still maintaining the base structure provided by the `Level` class.

## Additional Changes
- **Projectile Management**: Projectile management has been improved, with the `Level` class now handling the spawning and movement of projectiles for all levels.
- **UI Updates**: UI elements, such as score display and level progress, are now handled by the `Level` class, reducing the need for repetitive code in each level.
- **Event Handling**: Event listeners for player input, enemy actions, and game status have been centralized in the `Level` class for better efficiency.

# LevelView Class

## Overview
The `LevelView` class is responsible for managing the visual components of the game interface, such as displaying the heart count, win image, and game over screen. It ensures the appropriate UI elements are displayed during the game and handles updates like removing hearts when the player takes damage.

## Key Changes

### **Package Declaration**
- **Original Package**: `com.example.demo`
- **Updated Package**: `com.example.demo.Levels`
- **Purpose**: The class has been moved to the `com.example.demo.Levels` package to better organize the game code, especially as level-specific visual components may be required.

### **1. Adjustment of Loss Screen Position**
- **Original Loss Screen Position**:
  - `X Position`: `-160`
  - `Y Position`: `-375`
- **Updated Loss Screen Position**:
  - `X Position`: `-50`
  - `Y Position`: `-350`
- **Purpose**: The loss screen has been repositioned to better fit the game layout, ensuring it is displayed correctly within the game's window.

### **2. Displaying Win and Game Over Images**
- **Original Behavior**: Both the win and game over images were displayed unconditionally each time.
- **Updated Behavior**:
  - **Win Image**: The win image is now displayed only if it hasn’t been added to the root yet.
  - **Game Over Image**: The game over image is now displayed only if it hasn’t been added to the root yet.
- **Purpose**: To prevent the win and game over images from being added multiple times, improving performance and ensuring images are shown only once.

### **3. Improved Heart Removal Logic**
- **Original Heart Removal Logic**: The method `removeHearts()` simply iterated over the current number of hearts and removed the excess.
- **Updated Heart Removal Logic**:
  - The number of hearts to remove is now calculated by the difference between the current number of hearts and the hearts remaining.
- **Purpose**: To provide more accurate heart removal based on the current state of the heart display, ensuring the UI properly reflects the player's health.

### **4. Refactoring of Constructor**
- **Original Constructor**: `root`, `heartsToDisplay` passed directly into the constructor and used without explanation.
- **Updated Constructor**:
  - Additional explanation about the constructor's parameters.
  - More explicit initialization of `HeartDisplay`, `WinImage`, and `GameOverImage`.
- **Purpose**: To ensure better readability and understanding of the constructor’s role in initializing the visual components of the level.

## Additional Changes
- **General Refactoring**: The class now uses consistent method naming and structure to enhance readability and maintainability.
- **Error Handling**: No changes have been made to error handling; however, the class is now better structured to handle specific UI display scenarios.
- # LevelViewLevelTwo Class

# LevelViewLevelTwo Class

## Overview
The `LevelViewLevelTwo` class is a subclass of the `LevelView` class, responsible for managing the specific visual elements for level two. In particular, it adds and manages the shield image for the boss unit, which is a key feature in the gameplay of this level.

## Key Changes

### **Package Declaration**
- **Updated Package**: `com.example.demo.Levels`

### **1. Shield Image Addition**
- **Original Behavior**: The shield image was not included in the class.
- **Updated Behavior**:
  - The `ShieldImage` has been added as a level-specific element.
  - The shield image is placed at a fixed position (`X = 1150`, `Y = 500`) on the screen.
- **Purpose**: To visually represent the boss's shield in level two, which adds another layer of challenge and visual feedback to the player.

### **2. Constructor**
- **Original Constructor**: The `LevelViewLevelTwo` constructor was missing detailed documentation.
- **Updated Constructor**:
  - Now calls the `super(root, heartsToDisplay)` method to initialize inherited elements from the parent class `LevelView`.
  - Adds the shield image to the root container via the `addImagesToRoot()` method.
  - **Purpose**: To initialize the visual components specific to level two, including adding the shield image to the root Group.

### **3. Method to Add Images to Root**
- **Original Method**: No equivalent method for adding level-specific images.
- **Updated Method**: 
  - `addImagesToRoot()` is introduced to add the shield image to the root container.
  - This ensures the shield image is added to the scene and displayed during gameplay.
  - **Purpose**: To modularize the process of adding level-specific images, improving code structure and making it easier to modify the visual elements for level two.

### **4. Show and Hide Shield Methods**
- **Original Behavior**: There were no methods for showing or hiding the shield.
- **Updated Behavior**:
  - **showShield()**: Displays the shield image.
  - **hideShield()**: Hides the shield image.
- **Purpose**: To control the visibility of the shield during the gameplay, allowing for more dynamic interactions with the boss in level two.

### **5. Debugging Output**
- **Original Class**: No debugging output was present.
- **Updated Class**: A `System.out.println("shield added")` line was added inside `addImagesToRoot()` for debugging purposes.
- **Purpose**: To aid in troubleshooting and ensure that the shield image is being added to the scene as expected during development.


## Additional Changes
- **Class Refactoring**: The class is now more modular, with the addition of the `addImagesToRoot()` method that encapsulates the logic of adding level-specific visual elements.
- **Error Handling**: No specific error handling changes were made, though the added debugging output provides more insight during development.
# LevelOne Class

## Overview
The `LevelOne` class represents the first level in the game. It handles the initialization of the level, such as spawning enemies, managing the player's progress, checking for win/lose conditions, and transitioning to the next level when the kill target is met. The class was updated to enhance gameplay dynamics, improve functionality, and introduce new features like a kill count display.

## Key Changes

### **Package Declaration**
- **Updated Package**: `com.example.demo.Levels`

### **1. Background Image**
- **Original Background Image**: `/com/example/demo/images/background1.jpg`
- **Updated Background Image**: `/com/example/demo/images/background1.gif`
- **Purpose**: The background image was changed from a static JPEG to an animated GIF to improve the visual experience for players.

### **2. Total Enemies and Kill Target**
- **Original Total Enemies**: `5`
- **Updated Total Enemies**: `10`
- **Purpose**: The number of enemies to spawn was increased to make the level more challenging.

- **Original Kills to Advance**: `10`
- **Updated Kills to Advance**: `7`
- **Purpose**: The kill target was reduced, making it easier for players to advance to the next level.

### **3. Enemy Spawn Probability**
- **Original Spawn Probability**: `0.20`
- **Updated Spawn Probability**: `0.05`
- **Purpose**: The probability of spawning enemies was reduced to make the level less overwhelming, offering a more balanced difficulty curve.

### **4. Player Initial Health**
- **Original Player Health**: `5`
- **Updated Player Health**: `5` (No change)
- **Purpose**: Player health remains the same to maintain the initial difficulty level.

### **5. Kill Count Display**
- **New Feature**: A text element for displaying the player's kill count was added.
- **Purpose**: To provide visual feedback on the player's progress, making it easier to track kills and advancement toward the next level.

### **6. Game Over and Level Transition Logic**
- **Original Logic**: The level transitioned based on the player's destruction or reaching the kill target.
- **Updated Logic**:
  - Improved `checkIfGameOver()` method to handle both the loss and win conditions more clearly.
  - Added logic to prevent further updates after the level is marked as completed.
  - **Purpose**: To prevent the level from being processed once it’s completed, ensuring that the game ends or transitions appropriately.

### **7. Spawn Enemy Units Logic**
- **Original Logic**: The number of enemies spawned was determined by the current number of enemies and the total enemy limit.
- **Updated Logic**:
  - Added checks to prevent overlapping enemy planes by calling `isEnemyPlaneOverlapping()` before adding a new enemy.
  - The number of spawned enemies is dynamically adjusted based on the current number of enemies.
  - **Purpose**: To prevent overlapping enemy planes, ensuring a smoother gameplay experience and avoiding visual bugs.

### **8. LevelView Instantiation**
- **Original**: `instantiateLevelView()` returned an instance of `LevelView`.
- **Updated**: No change, still returns `LevelView`.
- **Purpose**: To instantiate the view for the level and display the relevant game elements on the screen.

### **9. Kill Count Text Initialization**
- **New Feature**: The `initializeKillCountText()` method was introduced to initialize and style the kill counter text.
  - Font: `Impact`, Size: `24`
  - Color: Black with a white stroke for visibility
  - Positioned at `(1200, 40)` on the screen
- **Purpose**: To display the kill count text on the screen, providing real-time feedback to the player on their progress.

### **10. Kill Count Update**
- **Original**: No mechanism for updating the kill count.
- **Updated**: The `updateKillCountText()` method updates the displayed kill count every time the scene is refreshed.
- **Purpose**: To continuously update and display the player's kill count as they progress through the level.

### **11. Update Scene Logic**
- **Original**: `updateScene()` method exists but only calls the superclass method.
- **Updated**: The `updateScene()` method now also calls `updateKillCountText()`, ensuring the kill count is updated every time the scene is rendered.
- **Purpose**: To keep the kill count in sync with the gameplay.

## Additional Changes
- **Class Refactoring**: The class has been refactored to make the kill count handling and level transitions more efficient.
- **Improved Enemy Handling**: The spawning logic was updated to ensure a balanced enemy appearance and prevent visual overlap.
- **Enhanced Gameplay Experience**: By updating enemy spawn mechanics, player health, and kill targets, the difficulty curve of the game was adjusted to be more engaging.

# Main Class

## Overview
The `Main` class serves as the entry point for the **Sky Battle** game. It is responsible for initializing the game window (stage), configuring its properties, and launching the game either by showing the main menu or directly launching the game. The class was refactored to improve organization, specifically for managing the main menu and game launch sequence.

## Key Changes

### **Package Declaration**
- **Original Package**: `com.example.demo.controller`

### **1. Launching the Game**
- **Original**: The game is launched through a `Controller` object, which is responsible for invoking the game's start and handling game flow.
- **Updated**: The game now directly shows the **MainMenu** by creating an instance of `MainMenu` and calling `showMenu()`.
- **Purpose**: Refactor for clarity and maintain a cleaner entry point. Instead of handling game flow through the controller in `Main`, it now just initializes and displays the main menu.

### **2. Stage Configuration**
- **Original**: The `Controller` class was responsible for setting up the game window (stage).
- **Updated**: The `Main` class now handles the stage configuration in the new `configureStage()` method.
- **Purpose**: Simplifies stage configuration by consolidating the setup logic into one method, making it clearer and more manageable.

### **3. Main Menu Handling**
- **Original**: There was no direct handling of the main menu in the `Main` class.
- **Updated**: A new method `showMainMenu()` was introduced. It creates a `MainMenu` object and calls `showMenu()` to display the menu on the screen.
- **Purpose**: This change modularizes the handling of the main menu, making it separate from the stage configuration and game initialization.

### **4. Game Launch Logic**
- **Original**: The game is launched through `Controller`'s `launchGame()` method after the stage setup.
- **Updated**: Game launch now focuses on showing the main menu first. The responsibility of launching the game is delegated to the `MainMenu` class.
- **Purpose**: This ensures the main menu is shown to the player first, which can lead to better user interaction and control.

### **5. Removed Reflection-Based Logic**
- **Original**: The `start` method used reflection-based logic to initialize the `Controller`, including handling exceptions such as `ClassNotFoundException` and `InvocationTargetException`.
- **Updated**: This reflection-based initialization was removed in favor of a more direct approach.
- **Purpose**: Simplifies the code and removes unnecessary complexity associated with reflection, making the `start()` method easier to understand and maintain.

## Additional Changes
- **Class Refactoring**: The game flow and main menu handling were refactored into separate methods (`configureStage()` and `showMainMenu()`) to improve code modularity and readability.
- **Game Launch**: The direct interaction with the `MainMenu` class improves the user experience by showing a clear entry point to the game.

---
# Pacakage Organization
com/example/demo/ ├── actor/ │ ├── ActiveActor.java │ ├── ActiveActorDestructible.java │ ├── Boss.java │ ├── BossProjectile.java │ ├── EnemyPlane.java │ ├── EnemyProjectile.java │ ├── FighterPlane.java │ └── Projectile.java │ ├── controller/ │ └── Controller.java │ ├── levels/ │ ├── LevelOne.java │ ├── LevelParent.java │ ├── LevelTwo.java │ ├── LevelView.java │ └── LevelViewLevelTwo.java │ ├── ui/ │ ├── GameoverImage.java │ ├── HeartDisplay.java │ ├── MainMenu.java │ ├── PauseMenu.java │ ├── ShieldImage.java │ └── WinImage.java │ ├── user/ │ ├── UserPlane.java │ └── UserProjectile.java │ ├── Main.java └── Destructible.java

## Unexpected Problems

During the debugging and assignment period, I encountered an unexpected issue. After committing the changes to the repository, the file was somehow deleted from my laptop. When I tried to download it again from my repository, I discovered that it was the original, unchanged version of the file. This set me back significantly, as I had to start over from scratch.

Despite trying various methods to resolve the issue and reaching out to my peers for help, it turned out to be a rather unusual situation. It was frustrating, but I eventually managed to recreate the missing work and push the necessary changes back to the repository.

This experience made me more cautious moving forward. I began creating zip files for my progress to ensure I had local backups in case something like this happened again.





  
