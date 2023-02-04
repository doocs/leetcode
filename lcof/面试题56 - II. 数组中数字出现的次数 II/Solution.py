class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        cnt = [0] * 32
        for x in nums:
            for i in range(32):
                cnt[i] += x & 1
                x >>= 1
        return sum(1 << i for i in range(32) if cnt[i] % 3)
