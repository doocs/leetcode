class Solution:
    def minimumTotalDistance(self, robot: List[int], factory: List[List[int]]) -> int:
        @cache
        def dfs(i, j):
            if i == len(robot):
                return 0
            if j == len(factory):
                return inf
            ans = dfs(i, j + 1)
            t = 0
            for k in range(factory[j][1]):
                if i + k == len(robot):
                    break
                t += abs(robot[i + k] - factory[j][0])
                ans = min(ans, t + dfs(i + k + 1, j + 1))
            return ans

        robot.sort()
        factory.sort()
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
