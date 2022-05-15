class Solution:
    def largestCombination(self, candidates: List[int]) -> int:
        ans = 0
        for i in range(32):
            t = 0
            for x in candidates:
                t += (x >> i) & 1
            ans = max(ans, t)
        return ans
