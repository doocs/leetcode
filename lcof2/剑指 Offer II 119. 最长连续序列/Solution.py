class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s, res = set(nums), 0
        for num in nums:
            if num - 1 not in s:
                t = 1
                next = num + 1
                while next in s:
                    t += 1
                    next += 1
                res = max(res, t)
        return res
