class Solution:
    def maxSelectedElements(self, nums: list[int]) -> int:
        nums.sort()
        ans = 1
        prev = -inf
        f0 = f1 = 1
        for x in nums:
            if x == prev:
                f1 = f0 + 1
            elif x == prev + 1:
                f0 += 1
                f1 += 1
            elif x == prev + 2:
                f0 = f1 + 1
                f1 = 1
            else:
                f0 = f1 = 1
            ans = max(ans, f0, f1)
            prev = x
        return ans
