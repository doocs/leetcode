class Solution:
    def countVisitedNodes(self, edges: List[int]) -> List[int]:
        n = len(edges)
        ans = [0] * n
        vis = [0] * n
        for i in range(n):
            if not ans[i]:
                cnt, j = 0, i
                while not vis[j]:
                    cnt += 1
                    vis[j] = cnt
                    j = edges[j]
                cycle, total = 0, cnt + ans[j]
                if not ans[j]:
                    cycle = cnt - vis[j] + 1
                    total = cnt
                j = i
                while not ans[j]:
                    ans[j] = max(total, cycle)
                    total -= 1
                    j = edges[j]
        return ans
