class Solution:
    def missingTwo(self, nums: List[int]) -> List[int]:
        res, n = 0, len(nums)
        for i in range(n):
            res ^= nums[i]
            res ^= i + 1
        res ^= n + 1
        res ^= n + 2
        pos = 0
        while (res & 1) == 0:
            pos += 1
            res >>= 1

        a = b = 0
        for num in nums:
            t = num >> pos
            if (t & 1) == 0:
                a ^= num
            else:
                b ^= num

        for i in range(1, n + 3):
            t = i >> pos
            if (t & 1) == 0:
                a ^= i
            else:
                b ^= i
        return [a, b]
