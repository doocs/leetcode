class Solution:
    def generateValidStrings(self, n: int, k: int) -> list[str]:
        def dfs(i: int, tot: int):
            if i >= n:
                ans.append("".join(path))
                return
            path.append("0")
            dfs(i + 1, tot)
            path.pop()
            if (not path or path[-1] == "0") and tot + i <= k:
                path.append("1")
                dfs(i + 1, tot + i)
                path.pop()

        ans = []
        path = []
        dfs(0, 0)
        return ans
