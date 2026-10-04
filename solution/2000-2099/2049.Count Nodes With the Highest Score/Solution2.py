class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        n = len(parents)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)
        ans = mx = 0
        sz = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                cnt = score = 1
                for j in g[i]:
                    if j != fa:
                        t = sz[j]
                        score *= t
                        cnt += t
                if n - cnt:
                    score *= n - cnt
                if mx < score:
                    mx = score
                    ans = 1
                elif mx == score:
                    ans += 1
                sz[i] = cnt
        return ans
