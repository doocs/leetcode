class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        martix = [[0] * n] * m
        for i in range(m):
            for j in range(n):
                if obstacleGrid[i][j] == 1:
                    martix[i][j] = 0
                else:
                    if i == 0 and j == 0:
                        martix[i][j] = 1
                    elif i == 0:
                        martix[i][j] = martix[i][j - 1]
                    elif j == 0:
                        martix[i][j] = martix[i - 1][j]
                    else:
                        martix[i][j] = martix[i - 1][j] + martix[i][j - 1]
        return martix[m - 1][n - 1]