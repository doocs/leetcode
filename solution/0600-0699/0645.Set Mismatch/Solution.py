class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        res = 0
        for num in nums:
            res ^= num
        for i in range(1, len(nums) + 1):
            res ^= i
        pos = 0
        while (res & 1) == 0:
            res >>= 1
            pos += 1
        a = b = 0
        for num in nums:
            if ((num >> pos) & 1) == 0:
                a ^= num
            else:
                b ^= num
        for i in range(1, len(nums) + 1):
            if ((i >> pos) & 1) == 0:
                a ^= i
            else:
                b ^= i
        for num in nums:
            if num == a:
                return [a, b]
        return [b, a]
