class Solution:
    def maxSubArrayLen(self, nums: List[int], k: int) -> int:
        mp = {0: -1}
        s = ans = 0
        for i, v in enumerate(nums):
            s += v
            if s - k in mp:
                ans = max(ans, i - mp[s - k])
            if s not in mp:
                mp[s] = i
        return ans
