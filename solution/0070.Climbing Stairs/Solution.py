class Solution:
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n<3:
            return n
        res=[None]*(n+1)
        res[1]=1
        res[2]=2
        for i in range(3,n+1):
            res[i]=res[i-1]+res[i-2]
        return res[n]