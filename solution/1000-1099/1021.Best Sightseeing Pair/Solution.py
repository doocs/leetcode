class Solution:

    def maxScoreSightseeingPair(self, A: List[int]) -> int:
        """
        构建A[i]+i和A[j]-j两个子序列，然后从后往前遍历，最值只要遍历一遍就可以
        如果从前往后，时间复杂度会变大
        """
        ai = [i + index for index, i in enumerate(A)]
        aj = [i - index for index, i in enumerate(A)]
        aj = aj[::-1]
        ans = 0
        t = float('-inf')
        for index, i in enumerate(ai[-2::-1]):
            t = max(aj[index], t)
            ans = max(ans, i + t)
        return ans
