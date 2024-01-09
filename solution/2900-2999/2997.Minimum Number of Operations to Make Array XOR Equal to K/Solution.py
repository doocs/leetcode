class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(20):
            v = 0
            for x in nums:
                v ^= x >> i & 1
            ans += (k >> i & 1) != v
        return ans
