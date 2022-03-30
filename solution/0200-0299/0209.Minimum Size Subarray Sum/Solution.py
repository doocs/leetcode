class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        s = [0] + list(accumulate(nums))
        n = len(nums)
        ans = n + 1
        for i, v in enumerate(s):
            t = v + target
            j = bisect_left(s, t)
            if j != n + 1:
                ans = min(ans, j - i)
        return 0 if ans == n + 1 else ans
