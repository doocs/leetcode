class Solution:
    def leastOpsExpressTarget(self, x: int, target: int) -> int:
        @cache
        def dfs(v: int) -> int:
            if x >= v:
                return min(v * 2 - 1, 2 * (x - v))
            k = 2
            while x**k < v:
                k += 1
            if x**k - v < v:
                return min(k + dfs(x**k - v), k - 1 + dfs(v - x ** (k - 1)))
            return k - 1 + dfs(v - x ** (k - 1))

        return dfs(target)
