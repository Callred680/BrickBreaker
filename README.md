# BrickBreaker
Simple 2D Java BrickBreaker game

# Description
This program will be a basic version of the brick breaker game built in entirely Java and utilizing MongoDB for future features [i.e. scoreboard and user customization]. Power ups and score will be developed in later versions, but the first stage will feature a simple game that runs properly.
- Standard of 3 rows, 36 bricks
    - Ability to change this in the future
- Default interface contains default values to be used in game alond with difficulty scaling/attributes
    - Sound class within interface contains default sound to be played on hits
        - Custom sound clips can be added
        - Built in library used so only AIFC, AIFF, AU, AND, and WAVE formats accepted

## Features to complete
1. Create map of bricks, paddle, and ball
2. Establish functionality for parts to move
3. Allow each part to interact with eachother
4. Add power ups
5. Add scoreboard and leaderboard
    * Design MongoDB to hold leaderboard
    * Must load leaderboard upon each startup
6. Add customization for color patterns
    * Saved with user
    * Must include schema in MongoDB to store information
        * Must include "sign up" option at start up

