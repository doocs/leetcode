class Solution:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        s = [0] * (n + 1)
        for i in range(1, n + 1):
            s[i] = s[i - 1] + nums[i - 1]
        ans1, ans2, fm, sm = 0, 0, 0, 0
        for i in range(n - firstLen - secondLen + 1):
            fm = max(fm, s[i + firstLen] - s[i])
            ans1 = max(fm + s[i + firstLen + secondLen] - s[i + firstLen], ans1)
        for i in range(n - firstLen - secondLen + 1):
            sm = max(sm, s[i + secondLen] - s[i])
            ans2 = max(sm + s[i + firstLen + secondLen] - s[i + secondLen], ans2)
        return max(ans1, ans2)
