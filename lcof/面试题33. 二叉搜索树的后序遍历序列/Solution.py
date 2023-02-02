class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        def dfs(l, r):
            if l >= r:
                return True
            v = postorder[r]
            i = l
            while i < r and postorder[i] < v:
                i += 1
            if any(x < v for x in postorder[i:r]):
                return False
            return dfs(l, i - 1) and dfs(i, r - 1)

        return dfs(0, len(postorder) - 1)
