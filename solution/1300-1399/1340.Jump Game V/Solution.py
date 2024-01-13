class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        @cache
        def dfs(i):
            ans = 1
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            return ans

        n = len(arr)
        return max(dfs(i) for i in range(n))
