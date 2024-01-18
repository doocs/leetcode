class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        ans, cnt = 0, 2
        d = 3000
        for a, b in pairwise(nums):
            if b - a == d:
                cnt += 1
            else:
                d = b - a
                cnt = 2
            ans += max(0, cnt - 2)
        return ans
