class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s, res = set(nums), 0
        for num in nums:
            if num - 1 not in s:
                t, next = 1, num + 1
                while next in s:
                    t, next = t + 1, next + 1
                res = max(res, t)
        return res
