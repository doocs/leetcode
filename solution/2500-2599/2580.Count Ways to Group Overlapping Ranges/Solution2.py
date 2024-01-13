class Solution:
    def countWays(self, ranges: List[List[int]]) -> int:
        ranges.sort()
        mx = -1
        mod = 10**9 + 7
        ans = 1
        for start, end in ranges:
            if start > mx:
                ans = ans * 2 % mod
            mx = max(mx, end)
        return ans
