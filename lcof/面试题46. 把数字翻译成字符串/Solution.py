class Solution:
    def translateNum(self, num: int) -> int:
        @cache
        def dfs(i):
            if i >= n - 1:
                return 1
            ans = dfs(i + 1)
            if s[i] == "1" or (s[i] == "2" and s[i + 1] < "6"):
                ans += dfs(i + 2)
            return ans

        s = str(num)
        n = len(s)
        return dfs(0)
