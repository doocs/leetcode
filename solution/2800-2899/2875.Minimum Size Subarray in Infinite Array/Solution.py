class Solution:
    def minSizeSubarray(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        n = len(nums)
        a = 0
        if target > s:
            a = n * (target // s)
            target -= target // s * s
        if target == s:
            return n
        pos = {0: -1}
        pre = 0
        b = inf
        for i, x in enumerate(nums):
            pre += x
            if (t := pre - target) in pos:
                b = min(b, i - pos[t])
            if (t := pre - (s - target)) in pos:
                b = min(b, n - (i - pos[t]))
            pos[pre] = i
        return -1 if b == inf else a + b
