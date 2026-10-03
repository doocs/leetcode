class Solution:
    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        n = len(s)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        d = [[] for _ in range(26)]
        ans = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                ans[i] = 1
                idx = ord(s[i]) - 97
                d[idx].append(i)
                stk.append((i, fa, 1))
                for j in g[i]:
                    stk.append((j, i, 0))
            else:
                idx = ord(s[i]) - 97
                k = d[idx][-2] if len(d[idx]) > 1 else fa
                if k != -1:
                    ans[k] += ans[i]
                d[idx].pop()
        return ans
