class Solution:
    def secondsToRemoveOccurrences(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == '0':
                cnt += 1
            elif cnt:
                ans = max(ans + 1, cnt)
        return ans
