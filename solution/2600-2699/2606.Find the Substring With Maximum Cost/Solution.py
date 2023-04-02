class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        d = {c: i for i, c in enumerate(chars)}
        ans = tot = mi = 0
        for c in s:
            v = vals[d[c]] if c in d else ord(c) - ord('a') + 1
            tot += v
            ans = max(ans, tot - mi)
            mi = min(mi, tot)
        return ans
