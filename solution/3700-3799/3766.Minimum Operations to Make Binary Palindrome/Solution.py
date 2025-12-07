p = []
for i in range(1 << 14):
    s = bin(i)[2:]
    if s == s[::-1]:
        p.append(i)


class Solution:
    def minOperations(self, nums: List[int]) -> List[int]:
        ans = []
        for x in nums:
            i = bisect_left(p, x)
            times = inf
            if i < len(p):
                times = min(times, p[i] - x)
            if i >= 1:
                times = min(times, x - p[i - 1])
            ans.append(times)
        return ans
