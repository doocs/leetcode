class Solution:
    def findLHS(self, nums: List[int]) -> int:
        counter = collections.Counter(nums)
        res = 0
        for num in nums:
            if num + 1 in counter:
                res = max(res, counter[num] + counter[num + 1])
        return res
