class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        mask = 0
        for x in nums:
            mask |= 1 << x.count("1")
        for i in count(0):
            if mask >> i & 1 ^ 1:
                return "1" * i + "0" * (len(nums) - i)
