class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        x = sum(nums) - x
        n = len(nums)
        s = 0
        seen = {0: -1}
        ans = inf
        for i, v in enumerate(nums):
            s += v
            if s not in seen:
                seen[s] = i
            if s - x in seen:
                j = seen[s - x]
                ans = min(ans, n - (i - j))
        return -1 if ans == inf else ans
