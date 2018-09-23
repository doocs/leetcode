class Solution:
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        res = [[0]*m]*n
        for i in range(n):
            for j in range(m):
                if i == 0 or j==0:
                    res[i][j] = 1
                else:
                    res[i][j] = res[i][j-1]+res[i-1][j]
        return res[n-1][m-1]