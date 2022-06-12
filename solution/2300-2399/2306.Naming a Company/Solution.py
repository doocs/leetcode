class Solution:
    def distinctNames(self, ideas: List[str]) -> int:
        g = defaultdict(int)
        for v in ideas:
            g[v[1:]] |= 1 << (ord(v[0]) - ord('a'))
        ans = 0
        cnt = [[0] * 26 for _ in range(26)]
        for mask in g.values():
            for i in range(26):
                if (mask >> i) & 1:
                    for j in range(26):
                        if ((mask >> j) & 1) == 0:
                            ans += cnt[i][j]
                else:
                    for j in range(26):
                        if (mask >> j) & 1:
                            cnt[i][j] += 1
        return ans << 1
