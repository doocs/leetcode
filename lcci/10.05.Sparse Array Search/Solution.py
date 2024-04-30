class Solution:
    def findString(self, words: List[str], s: str) -> int:
        def dfs(i: int, j: int) -> int:
            if i > j:
                return -1
            mid = (i + j) >> 1
            l = dfs(i, mid - 1)
            if l != -1:
                return l
            if words[mid] == s:
                return mid
            return dfs(mid + 1, j)

        return dfs(0, len(words) - 1)
