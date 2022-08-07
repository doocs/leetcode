class Solution:
    def rearrangeCharacters(self, s: str, target: str) -> int:
        cnt = Counter(s)
        cnt2 = Counter(target)
        ans = inf
        for c, v in cnt2.items():
            if cnt[c] < v:
                return 0
            ans = min(ans, cnt[c] // v)
        return ans
