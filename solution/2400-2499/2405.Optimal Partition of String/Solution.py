class Solution:
    def partitionString(self, s: str) -> int:
        ss = set()
        ans = 1
        for c in s:
            if c in ss:
                ans += 1
                ss = set()
            ss.add(c)
        return ans
