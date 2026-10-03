class Solution:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = [0] * n
        size = [0] * n
        stk = [(0, -1, 0, 0)]
        while stk:
            i, fa, d, state = stk.pop()
            if state == 0:
                ans[0] += d
                stk.append((i, fa, d, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, d + 1, 0))
            else:
                size[i] = 1
                for j in g[i]:
                    if j != fa:
                        size[i] += size[j]
        walk = [(0, -1, ans[0])]
        while walk:
            i, fa, t = walk.pop()
            ans[i] = t
            for j in g[i]:
                if j != fa:
                    walk.append((j, i, t - size[j] + n - size[j]))
        return ans
