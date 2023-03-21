class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        @cache
        def dfs(i, j):
            if i == j:
                return 0
            ans = a = 0
            for k in range(i, j):
                a += stoneValue[k]
                b = s[j + 1] - s[i] - a
                if a < b:
                    if ans >= a * 2:
                        continue
                    ans = max(ans, a + dfs(i, k))
                elif a > b:
                    if ans >= b * 2:
                        break
                    ans = max(ans, b + dfs(k + 1, j))
                else:
                    ans = max(ans, a + dfs(i, k), b + dfs(k + 1, j))
            return ans

        s = list(accumulate(stoneValue, initial=0))
        return dfs(0, len(stoneValue) - 1)
