class Solution:
    def lexSmallest(self, s: str) -> str:
        ans = s
        for k in range(1, len(s) + 1):
            t1 = s[:k][::-1] + s[k:]
            t2 = s[:-k] + s[-k:][::-1]
            ans = min(ans, t1, t2)
        return ans
