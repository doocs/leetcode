class Solution:
    def maxSumOfThreeSubarrays(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        pre = [[] for _ in range(n)]
        suf = [[] for _ in range(n)]
        t = idx = 0
        for i in range(n - k + 1):
            if (cur := s[i + k] - s[i]) > t:
                pre[i + k - 1] = [cur, i]
                t, idx = pre[i + k - 1]
            else:
                pre[i + k - 1] = [t, idx]
        t = idx = 0
        for i in range(n - k, -1, -1):
            if (cur := s[i + k] - s[i]) >= t:
                suf[i] = [cur, i]
                t, idx = suf[i]
            else:
                suf[i] = [t, idx]
        t = 0
        ans = []
        for i in range(k, n - 2 * k + 1):
            if (cur := s[i + k] - s[i] + pre[i - 1][0] + suf[i + k][0]) > t:
                ans = [pre[i - 1][1], i, suf[i + k][1]]
                t = cur
        return ans
