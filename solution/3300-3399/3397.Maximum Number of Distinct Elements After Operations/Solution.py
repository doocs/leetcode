class Solution:
    def maxDistinctElements(self, nums: list[int], k: int) -> int:
        nums.sort()
        ans = 0
        pre = -inf
        for x in nums:
            cur = min(x + k, max(x - k, pre + 1))
            if cur > pre:
                ans += 1
                pre = cur
        return ans
