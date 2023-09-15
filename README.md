# BrickBreaker
Simple 2D Java BrickBreaker game based on 1999 arcade game

# Description
This program will be a basic version of the brick breaker game built in entirely Java and utilizing MongoDB for future features [i.e. scoreboard and user customization]. Power ups and score will be developed in later versions, but the first stage will feature a simple game that runs properly.
- Standard of 3 rows, 36 bricks
    ~~ Ability to change this in the future~~
    - Added ability to select difficulty
        * Easy  (Paddle Speed: 2, Ball Speed: 1)
        * Medium    (Paddle Speed: 2, Ball Speed: 2)
        * Hard  (Paddle Speed: 1, Ball Speed: 2)
        * Impossible    (Paddle Speed: 2, Ball Speed: 4)
- Default interface contains default values to be used in game alond with difficulty scaling/attributes
    - Sound class within interface contains default sound to be played on hits
        - Custom sound clips can be added
        - Built in library used so only AIFC, AIFF, AU, AND, and WAVE formats accepted
    - Utilized Freesound.org for sound clips
- User sign in for game stats and customization storage
    - Assumes only valid inputs and unique user IDs are inputted
        - Input verification and error handling TODO
    - Info and stats stored in XML file
    

## Features to complete
1. Create map of bricks, paddle, and ball ✅
2. Establish functionality for parts to move ✅
3. Allow each part to interact with eachother ✅
    * Add sound bits on hits ✅
4. Add power ups ⭕
5. Add scoreboard and leaderboard ⭕
    * Design MongoDB to hold leaderboard ⭕
    * Must load leaderboard upon each startup ⭕
6. Add customization for color patterns ⭕
    * Saved with user ⭕
    * Must include schema in MongoDB to store information ⭕
        * Must include "sign up" option at start up ⭕
7. Scoring system based on:
    - Time to beat ⭕
    - Difficulty level ✅
        - Only based on number of bricks as of right now
    - Power ups ⭕
    - Random factor ("Rogue like") ⭕

### Future Additions
* Further Optimization for rendering
* Leaderboards using XML files for local storing and retrieval
* User login/personalization screen


