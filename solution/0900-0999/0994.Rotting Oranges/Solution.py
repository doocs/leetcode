'''
https://leetcode.com/problems/rotting-oranges/

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Runtime: 44 ms, faster than 98.53% of Python3 online submissions for Rotting Oranges.
Memory Usage: 12.7 MB, less than 100.00% of Python3 online submissions for Rotting Oranges.
'''

from collections import deque
class Solution:
    def orangesRotting(self, grid) -> int:
    # Setting up common used variable
        max_row = len(grid)
        max_col = len(grid[0])
        rotten_oranges = []

        # Put all of initial rottens orange in one array
        for row in range(max_row):
            for col in range(max_col):
                if grid[row][col] == 2:
                    rotten_oranges.append((row,col))
        # Hepler function. If the grid contain fresh orange, it will become rotten
        def spread_rotten(row,col):
            nonlocal grid, temp_rotten_oranges
            if grid[row][col] == 1:
                temp_rotten_oranges.append((row,col))
                grid[row][col] = 2

        # Each loop represent a minutes 
        # In a loop, we use a temp array to store all of the rotten array got spreaded in that minutes
        # The loop only ends when the temp array is empty
        time = 0
        while True:
            temp_rotten_oranges = []
            for rotten_orange in rotten_oranges:
                row, col = rotten_orange
                if row > 0:
                    spread_rotten(row-1, col)
                if col > 0:
                    spread_rotten(row, col-1)
                if row < max_row - 1:
                    spread_rotten(row+1, col)
                if col < max_col - 1:
                    spread_rotten(row, col+1)
            if not temp_rotten_oranges:
                break
            rotten_oranges = temp_rotten_oranges.copy()
            time += 1

        # Check the remaing array, return -1 if there's still a fresh orange left
        for row in range(max_row):
            for col in range(max_col):
                if grid[row][col] == 1:
                    return -1
            
        return time



