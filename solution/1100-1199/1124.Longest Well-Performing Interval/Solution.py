class Solution:
    def longestWPI(self, hours: List[int]) -> int:
        ans = s = 0
        pos = {}
        for i, x in enumerate(hours):
            s += 1 if x > 8 else -1
            if s > 0:
                ans = i + 1
            elif s - 1 in pos:
                ans = max(ans, i - pos[s - 1])
            if s not in pos:
                pos[s] = i
        return ans
