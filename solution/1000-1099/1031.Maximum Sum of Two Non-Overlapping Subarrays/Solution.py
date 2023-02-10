class Solution:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        ans = t = 0
        i = firstLen
        while i + secondLen - 1 < n:
            t = max(t, s[i] - s[i - firstLen])
            ans = max(ans, t + s[i + secondLen] - s[i])
            i += 1
        t = 0
        i = secondLen
        while i + firstLen - 1 < n:
            t = max(t, s[i] - s[i - secondLen])
            ans = max(ans, t + s[i + firstLen] - s[i])
            i += 1
        return ans
