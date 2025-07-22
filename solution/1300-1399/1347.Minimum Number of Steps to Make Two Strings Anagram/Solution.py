class Solution:
    def minSteps(self, s: str, t: str) -> int:
        cnt = Counter(s)
        ans = 0
        for c in t:
            cnt[c] -= 1
            ans += cnt[c] < 0
        return ans
