class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n -= n & (-n)
            ans += 1
        return ans
