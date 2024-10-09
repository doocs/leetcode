class Solution:
    def canAliceWin(self, nums: List[int]) -> bool:
        a = sum(x for x in nums if x < 10)
        b = sum(x for x in nums if x > 9)
        return a != b
