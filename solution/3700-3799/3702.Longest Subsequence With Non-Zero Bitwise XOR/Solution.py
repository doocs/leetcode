class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        n = len(nums)
        xor = cnt0 = 0
        for x in nums:
            xor ^= x
            cnt0 += int(x == 0)
        if xor:
            return n
        if cnt0 == n:
            return 0
        return n - 1
