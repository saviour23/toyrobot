# Toyrobot

##Toy Robot Simulator Description

The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units.
There are no other obstructions on the table surface.
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction
Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.

###Commands format

PLACE X,Y,F

MOVE

LEFT

RIGHT

REPORT


##How the commands work

1.PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.

2.The origin (0,0) can be considered to be the SOUTH WEST most corner.

3.The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command

4.The application should discard all commands in the sequence until a valid PLACE command has been executed.

5.MOVE will move the toy robot one unit forward in the direction it is currently facing. LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.

6.REPORT will announce the X, Y and F of the robot - this can be in any form, but standard output is sufficient.

7.A robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.
                   
 ##Constraints:
 
1.The toy robot must not fall off the table during movement (this also includes the initial placement of the toy robot).

2.Any move that would cause the robot to fall must be ignored.
