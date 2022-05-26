class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        def dfs(postorder):
            if not postorder:
                return True
            v = postorder[-1]
            i = 0
            while i < len(postorder) and postorder[i] < v:
                i += 1
            if any(x < v for x in postorder[i:]):
                return False
            return dfs(postorder[:i]) and dfs(postorder[i:-1])

        return dfs(postorder)
