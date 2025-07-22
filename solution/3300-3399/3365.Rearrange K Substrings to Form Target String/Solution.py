class Solution:
    def isPossibleToRearrange(self, s: str, t: str, k: int) -> bool:
        cnt = Counter()
        n = len(s)
        m = n // k
        for i in range(0, n, m):
            cnt[s[i : i + m]] += 1
            cnt[t[i : i + m]] -= 1
        return all(v == 0 for v in cnt.values())
