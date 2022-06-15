class Solution:
    def catMouseGame(self, graph: List[List[int]]) -> int:
        @cache
        def dfs(i, j, k):
            # mouse / cat / steps
            if k >= 2 * len(graph):
                return 0  # tie
            if i == 0:
                return 1  # mouse wins
            if i == j:
                return 2  # cat wins
            if k % 2:  # cat’s turn
                tie = False
                for next in graph[j]:
                    if next == 0:
                        continue
                    x = dfs(i, next, k + 1)
                    if x == 2:
                        return 2
                    if x == 0:
                        # continue to find if exists winning case
                        tie = True
                if tie:
                    return 0
                return 1
            else:  # mouse's turn
                tie = False
                for next in graph[i]:
                    x = dfs(next, j, k + 1)
                    if x == 1:
                        return 1
                    if x == 0:
                        # continue to find if exists winning case
                        tie = True
                if tie:
                    return 0
                return 2

        return dfs(1, 2, 0)
