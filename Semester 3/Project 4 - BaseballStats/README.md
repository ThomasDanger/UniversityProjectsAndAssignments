# Project 4 - BaseballStats

## What it does

This program parses a keyfile and a list of coded player statistics to create a hashmap of player statistics that can be interpretted by the computer

## keyfile.txt

    ## OUTS ##
    1-3
    2-3
    3u
    3-1
    3-4
    ...

    ## STRIKEOUT ##
    K

    ## HITS ##
    1B
    2B
    3B
    HR
    
    ...
    
## stats.txt
    A Iggy F8
    A Lemmy 5-3
    A Roy 6-3
    H Toad 5-3
    H Peach E7
    H Mario BB
    H Donkey_Kong 1B
    ...
    
 ## Example Output
    AWAY
    Bowser	2	0	0	1	0	0	0.000	0.000
    Bowser_Jr	0	0	1	0	0	0	0.000	1.000
    Iggy	2	0	0	0	0	0	0.000	0.000
    Larry	1	1	0	0	0	0	1.000	1.000
    Lemmy	1	0	1	0	0	0	0.000	0.500
    Ludwig	1	0	0	0	0	0	0.000	0.000
    Morton	1	0	0	1	0	0	0.000	0.000
    Roy	2	1	0	0	0	0	0.500	0.500
    Wendy	1	0	0	0	0	0	0.000	0.000

    HOME
    Daisy	1	0	0	0	0	0	0.000	0.000
    Diddy_Kong	1	0	0	0	0	0	0.000	0.000
    Donkey_Kong	1	1	0	0	0	0	1.000	1.000
    Luigi	1	0	0	0	0	0	0.000	0.000
    Mario	0	0	1	0	0	0	0.000	1.000
    Peach	2	0	0	1	0	0	0.000	0.000
    Toad	2	0	0	0	0	0	0.000	0.000
    Toadette	1	0	0	1	0	0	0.000	0.000
    Yoshi	1	0	0	0	0	0	0.000	0.000

    LEAGUE LEADERS
    BATTING AVERAGE
    1.000	Larry, Donkey_Kong
    0.500	Roy

    ON-BASE PERCENTAGE
    1.000	Bowser_Jr, Larry, Donkey_Kong, Mario

    HITS
    1	Larry, Roy, Donkey_Kong

    WALKS
    1	Bowser_Jr, Lemmy, Mario

    STRIKEOUTS
    0	Bowser_Jr, Iggy, Larry, Lemmy, Ludwig, Roy, Wendy, Daisy, Diddy_Kong, Donkey_Kong, Luigi, Mario, Toad, Yoshi

    HIT BY PITCH
    0	Bowser, Bowser_Jr, Iggy, Larry, Lemmy, Ludwig, Morton, Roy, Wendy, Daisy, Diddy_Kong, Donkey_Kong, Luigi, Mario, Peach, Toad, Toadette, Yoshi
