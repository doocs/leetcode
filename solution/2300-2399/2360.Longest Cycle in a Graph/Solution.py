class Solution:
    def longestCycle(self, edges: List[int]) -> int:
        n = len(edges)
        vis = [False] * n
        ans = -1
        for i in range(n):
            if vis[i]:
                continue
            curr = i
            cycle = []
            while curr != -1 and not vis[curr]:
                vis[curr] = True
                cycle.append(curr)
                curr = edges[curr]
            if curr == -1:
                continue
            for j, v in enumerate(cycle):
                if v == curr:
                    ans = max(ans, len(cycle) - j)
                    break
        return ans
