class Solution:
    def constructDistancedSequence(self, n: int) -> List[int]:
        def dfs(u):
            if u == n * 2:
                return True
            if path[u]:
                return dfs(u + 1)
            for i in range(n, 1, -1):
                if cnt[i] and u + i < n * 2 and path[u + i] == 0:
                    cnt[i] = 0
                    path[u] = path[u + i] = i
                    if dfs(u + 1):
                        return True
                    path[u] = path[u + i] = 0
                    cnt[i] = 2
            if cnt[1]:
                cnt[1], path[u] = 0, 1
                if dfs(u + 1):
                    return True
                path[u], cnt[1] = 0, 1
            return False

        path = [0] * (n * 2)
        cnt = [2] * (n * 2)
        cnt[1] = 1
        dfs(1)
        return path[1:]
