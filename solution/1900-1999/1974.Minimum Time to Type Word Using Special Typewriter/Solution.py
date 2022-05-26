class Solution:
    def minTimeToType(self, word: str) -> int:
        ans = prev = 0
        for c in word:
            curr = ord(c) - ord('a')
            t = abs(prev - curr)
            t = min(t, 26 - t)
            ans += t + 1
            prev = curr
        return ans
