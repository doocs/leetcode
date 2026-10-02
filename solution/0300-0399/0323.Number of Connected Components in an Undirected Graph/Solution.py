class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        ans = 0
        for i in range(n):
            if i in vis:
                continue
            ans += 1
            vis.add(i)
            stack = [i]
            while stack:
                node = stack.pop()
                for neighbor in g[node]:
                    if neighbor not in vis:
                        vis.add(neighbor)
                        stack.append(neighbor)
        return ans
