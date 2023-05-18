class Solution:
    def maxSubArrayLen(self, nums: List[int], k: int) -> int:
        d = {0: -1}
        ans = s = 0
        for i, x in enumerate(nums):
            s += x
            if s - k in d:
                ans = max(ans, i - d[s - k])
            if s not in d:
                d[s] = i
        return ans
