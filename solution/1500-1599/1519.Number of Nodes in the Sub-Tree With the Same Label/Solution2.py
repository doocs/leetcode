class Solution:
    def countSubTrees(self, n: int, edges: List[List[int]], labels: str) -> List[int]:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = [0] * n
        cnt = Counter()
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                ans[i] -= cnt[labels[i]]
                cnt[labels[i]] += 1
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                ans[i] += cnt[labels[i]]
        return ans
