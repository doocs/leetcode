class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        graph = defaultdict(list)
        for pair in adjacentPairs:
            graph[pair[0]].append(pair[1])
            graph[pair[1]].append(pair[0])
        ans = []
        vis = set()

        def dfs(idx):
            if idx in vis:
                return
            vis.add(idx)
            ans.append(idx)
            for nxt in graph[idx]:
                dfs(nxt)

        start = -1
        for idx, adj in graph.items():
            if len(adj) == 1:
                start = idx
                break

        dfs(start)
        return ans
