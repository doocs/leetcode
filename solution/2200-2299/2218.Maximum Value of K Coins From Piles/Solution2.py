class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        f = [0] * (k + 1)
        for nums in piles:
            s = list(accumulate(nums, initial=0))
            for j in range(k, -1, -1):
                for h, w in enumerate(s):
                    if j < h:
                        break
                    f[j] = max(f[j], f[j - h] + w)
        return f[k]
