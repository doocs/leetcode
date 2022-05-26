class Solution:
    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """
        res = [0] * (n + 1)
        res[0] = 1
        for i in range(1, n + 1):
            for j in range(i):
                res[i] += res[j] * res[i - j - 1]
        return res[n]
