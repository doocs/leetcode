class Solution:
    def minOperations(self, nums: List[int]) -> int:
        mx = ans = 0
        for v in nums:
            ans += max(0, mx + 1 - v)
            mx = max(mx + 1, v)
        return ans
