class Solution:
    def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
        def dfs(i: int, j: int) -> Optional[TreeNode]:
            if i > j:
                return None
            root = TreeNode(preorder[i])
            l, r = i + 1, j + 1
            while l < r:
                mid = (l + r) >> 1
                if preorder[mid] > preorder[i]:
                    r = mid
                else:
                    l = mid + 1
            root.left = dfs(i + 1, l - 1)
            root.right = dfs(l, j)
            return root

        return dfs(0, len(preorder) - 1)
