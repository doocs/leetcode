class Solution:
    def totalHammingDistance(self, nums: List[int]) -> int:
        ans = 0
        for i in range(31):
            a = b = 0
            for v in nums:
                t = (v >> i) & 1
                if t:
                    a += 1
                else:
                    b += 1
            ans += a * b
        return ans
