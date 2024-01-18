class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        @cache
        def dfs(v):
            if v == 0:
                return 0
            if v < 10 and v % k:
                return inf
            i = 0
            t = inf
            while (x := i * 10 + k) <= v:
                t = min(t, dfs(v - x))
                i += 1
            return t + 1

        if num == 0:
            return 0
        if k == 0:
            return -1 if num % 10 else 1
        ans = dfs(num)
        return -1 if ans >= inf else ans
