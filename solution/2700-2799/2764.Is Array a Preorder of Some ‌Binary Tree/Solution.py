class Solution:
    def isPreorder(self, nodes: List[List[int]]) -> bool:
        def dfs(i: int) -> int:
            nonlocal k
            if i != nodes[k][0]:
                return False
            k += 1
            return all(dfs(j) for j in g[i])

        g = defaultdict(list)
        for i, p in nodes:
            g[p].append(i)
        k = 0
        return dfs(nodes[0][0]) and k == len(nodes)
