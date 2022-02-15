# """
# This is the robot's control interface.
# You should not implement it, or speculate about its implementation
# """
# class Robot:
#    def move(self):
#        """
#        Returns true if the cell in front is open and robot moves into the cell.
#        Returns false if the cell in front is blocked and robot stays in the current cell.
#        :rtype bool
#        """
#
#    def turnLeft(self):
#        """
#        Robot will stay in the same cell after calling turnLeft/turnRight.
#        Each turn will be 90 degrees.
#        :rtype void
#        """
#
#    def turnRight(self):
#        """
#        Robot will stay in the same cell after calling turnLeft/turnRight.
#        Each turn will be 90 degrees.
#        :rtype void
#        """
#
#    def clean(self):
#        """
#        Clean the current cell.
#        :rtype void
#        """


class Solution:
    def cleanRoom(self, robot):
        """
        :type robot: Robot
        :rtype: None
        """

        def back():
            robot.turnRight()
            robot.turnRight()
            robot.move()
            robot.turnRight()
            robot.turnRight()

        def dfs(i, j, d):
            vis.add((i, j))
            robot.clean()
            for k in range(4):
                nd = (d + k) % 4
                x, y = i + dirs[nd][0], j + dirs[nd][1]
                if (x, y) not in vis and robot.move():
                    dfs(x, y, nd)
                    back()
                robot.turnRight()

        vis = set()
        dirs = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        dfs(0, 0, 0)
