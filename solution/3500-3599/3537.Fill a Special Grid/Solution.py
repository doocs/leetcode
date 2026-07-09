class Solution:
    def specialGrid(self, n: int) -> List[List[int]]:
        def dfs(x: int, y: int, k: int):
            if k == 1:
                nonlocal val
                ans[x][y] = val
                val += 1
                return

            dfs(x, y, k // 2)
            dfs(x + k // 2, y, k // 2)
            dfs(x + k // 2, y - k // 2, k // 2)
            dfs(x, y - k // 2, k // 2)

        m = 1 << n
        ans = [[0] * m for _ in range(m)]
        val = 0
        dfs(0, m - 1, m)
        return ans
