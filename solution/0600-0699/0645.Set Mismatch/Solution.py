class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        eor = 0
        for i, x in enumerate(nums, 1):
            eor ^= i ^ x
        a = 0
        lb = eor & -eor
        for i, x in enumerate(nums, 1):
            if i & lb:
                a ^= i
            if x & lb:
                a ^= x
        b = eor ^ a
        for x in nums:
            if x == a:
                return [a, b]
        return [b, a]
