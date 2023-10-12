class Solution:
    def minimalExecTime(self, root: TreeNode) -> float:
        def dfs(root: TreeNode) -> Tuple[int, int]:
            if not root:
                return 0, 0
            s1, t1 = dfs(root.left)
            s2, t2 = dfs(root.right)
            return s1 + s2 + root.val, max(t1, t2, (s1 + s2) / 2) + root.val

        return dfs(root)[1]
