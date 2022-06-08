class Solution(object):
    def numWays(self, n, relation, k):
        """
        :type n: int
        :type relation: List[List[int]]
        :type k: int
        :rtype: int
        """
        dp = []
        for i in range(0,k+1):
            tmp = [0]*(n)
            dp.append(tmp)

        dp[0][0] = 1

        for i in range(1,k+1):
            for j in range(0,n):
                sum = 0
                for p in relation:
                    if(p[1] == j):
                        sum = sum + dp[i-1][p[0]]
                dp[i][j] = sum

        return dp[k][n-1]
