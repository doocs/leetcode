class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        eor, n = 0, len(nums)
        for i in range(1, n + 1):
            eor ^= i ^ nums[i - 1]
        diff = eor & (~eor + 1)
        a = 0
        for i in range(1, n + 1):
            if (nums[i - 1] & diff) == 0:
                a ^= nums[i - 1]
            if (i & diff) == 0:
                a ^= i
        b = eor ^ a
        for num in nums:
            if a == num:
                return [a, b]
        return [b, a]
