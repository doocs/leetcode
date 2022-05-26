class Solution:
    def longestWPI(self, hours: List[int]) -> int:
        ans = s = 0
        seen = {}
        for i, h in enumerate(hours):
            s += 1 if h > 8 else -1
            if s > 0:
                ans = i + 1
            else:
                if s not in seen:
                    seen[s] = i
                if s - 1 in seen:
                    ans = max(ans, i - seen[s - 1])
        return ans
