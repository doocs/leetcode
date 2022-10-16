class Solution:
    def countDistinctIntegers(self, nums: List[int]) -> int:
        s = set(nums)
        for x in nums:
            y = int(str(x)[::-1])
            s.add(y)
        return len(s)
