class Solution:
    def findMaximumLength(self, nums: List[int]) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        f = [0] * (n + 1)
        pre = [0] * (n + 2)
        for i in range(1, n + 1):
            pre[i] = max(pre[i], pre[i - 1])
            f[i] = f[pre[i]] + 1
            j = bisect_left(s, s[i] * 2 - s[pre[i]])
            pre[j] = i
        return f[n]
