class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        bits = [0 for _ in range(32)]
        for num in nums:
            for i in range(32):
                bits[i] += num & 1
                num >>= 1
        res = 0
        for i in range(32):
            if bits[i] % 3 == 1:
                res += 1 << i
        return res
