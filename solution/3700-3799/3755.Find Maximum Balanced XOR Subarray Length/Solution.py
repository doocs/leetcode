class Solution:
    def maxBalancedSubarray(self, nums: List[int]) -> int:
        d = {(0, 0): -1}
        a = b = 0
        ans = 0
        for i, x in enumerate(nums):
            a ^= x
            b += 1 if x % 2 == 0 else -1
            if (a, b) in d:
                ans = max(ans, i - d[(a, b)])
            else:
                d[(a, b)] = i
        return ans
