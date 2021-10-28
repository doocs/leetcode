class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        ans = []

        def dfs(i, path):
            if i == len(graph) - 1:
                ans.append(path.copy())
                return
            for j in graph[i]:
                path.append(j)
                dfs(j, path)
                path.pop(-1)

        dfs(0, [0])
        return ans
