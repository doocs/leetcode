class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        def dfs(u):
            for v in g[u]:
                dfs(v)
                value[u] += value[v]
                counter[u] += counter[v]
            if value[u] == 0:
                counter[u] = 0

        g = defaultdict(list)
        for i, p in enumerate(parent):
            if p != -1:
                g[p].append(i)
        counter = [1] * nodes
        dfs(0)
        return counter[0]
