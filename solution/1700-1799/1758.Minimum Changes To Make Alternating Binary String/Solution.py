class Solution:
    def minOperations(self, s: str) -> int:
        cnt = 0
        for i, c in enumerate(s):
            cnt += c == '01'[i & 1]
        return min(cnt, len(s) - cnt)
