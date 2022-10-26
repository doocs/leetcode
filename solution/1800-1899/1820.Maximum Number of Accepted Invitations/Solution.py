class Solution:
    def maximumInvitations(self, grid: List[List[int]]) -> int:
        def find(i):
            for j, v in enumerate(grid[i]):
                if v and j not in vis:
                    vis.add(j)
                    if match[j] == -1 or find(match[j]):
                        match[j] = i
                        return True
            return False

        m, n = len(grid), len(grid[0])
        match = [-1] * n
        ans = 0
        for i in range(m):
            vis = set()
            ans += find(i)
        return ans
