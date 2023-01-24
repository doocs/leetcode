class Solution:
    def getMaximumXor(self, nums: List[int], maximumBit: int) -> List[int]:
        ans = []
        xs = reduce(xor, nums)
        mask = (1 << maximumBit) - 1
        for x in nums[::-1]:
            k = xs ^ mask
            ans.append(k)
            xs ^= x
        return ans
