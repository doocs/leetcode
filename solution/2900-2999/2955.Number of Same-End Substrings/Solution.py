class Solution:
    def sameEndSubstringCount(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        cs = set(s)
        cnt = {c: [0] * (n + 1) for c in cs}
        for i, a in enumerate(s, 1):
            for c in cs:
                cnt[c][i] = cnt[c][i - 1]
            cnt[a][i] += 1
        ans = []
        for l, r in queries:
            t = r - l + 1
            for c in cs:
                x = cnt[c][r + 1] - cnt[c][l]
                t += x * (x - 1) // 2
            ans.append(t)
        return ans
