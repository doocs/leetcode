class Solution:
    def countPrimeSetBits(self, L, R):
        """
        :type L: int
        :type R: int
        :rtype: int
        """
        res = 0
        flag = [2, 3, 5, 7, 11, 13, 17, 19]
        for i in range(L, R + 1):
            if bin(i).count('1') in flag:
                res += 1
        return res
