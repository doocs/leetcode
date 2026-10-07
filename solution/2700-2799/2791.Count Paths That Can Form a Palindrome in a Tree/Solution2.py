class Solution:
    def countPalindromePaths(self, parent: List[int], s: str) -> int:
        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append((i, 1 << (ord(s[i]) - ord('a'))))
        ans = 0
        cnt = Counter({0: 1})
        stk = [(0, 0)]
        while stk:
            i, xor = stk.pop()
            for j, v in g[i]:
                x = xor ^ v
                ans += cnt[x]
                for k in range(26):
                    ans += cnt[x ^ (1 << k)]
                cnt[x] += 1
                stk.append((j, x))
        return ans
