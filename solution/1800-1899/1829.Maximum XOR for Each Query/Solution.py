class Solution:
    def getMaximumXor(self, nums: List[int], maximumBit: int) -> List[int]:
        n = len(nums)
        s = [0] * (n + 1)
        for i, x in enumerate(nums):
            s[i + 1] = s[i] ^ x
        ans = []
        for x in s[:0:-1]:
            t = 0
            for i in range(maximumBit):
                if ((x >> i) & 1) == 0:
                    t |= 1 << i
            ans.append(t)
        return ans
