class Solution:
    def splitPainting(self, segments: List[List[int]]) -> List[List[int]]:
        d = defaultdict(int)
        for l, r, c in segments:
            d[l] += c
            d[r] -= c
        s = sorted([[k, v] for k, v in d.items()])
        n = len(s)
        for i in range(1, n):
            s[i][1] += s[i - 1][1]
        return [[s[i][0], s[i + 1][0], s[i][1]] for i in range(n - 1) if s[i][1]]
