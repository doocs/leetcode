def max(a: int, b: int) -> int:
    return a if a > b else b


class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= j:
                return 0
            ans = l = 0
            r = s[j + 1] - s[i]
            for k in range(i, j):
                l += stoneValue[k]
                r -= stoneValue[k]
                if l < r:
                    if ans >= l * 2:
                        continue
                    ans = max(ans, l + dfs(i, k))
                elif l > r:
                    if ans >= r * 2:
                        break
                    ans = max(ans, r + dfs(k + 1, j))
                else:
                    ans = max(ans, max(l + dfs(i, k), r + dfs(k + 1, j)))
            return ans

        s = list(accumulate(stoneValue, initial=0))
        return dfs(0, len(stoneValue) - 1)
