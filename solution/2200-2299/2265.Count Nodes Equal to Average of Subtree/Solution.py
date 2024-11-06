class Solution:
    def averageOfSubtree(self, root: TreeNode) -> int:
        def dfs(root) -> tuple:
            if not root:
                return 0, 0
            ls, ln = dfs(root.left)
            rs, rn = dfs(root.right)
            s = ls + rs + root.val
            n = ln + rn + 1
            nonlocal ans
            ans += int(s // n == root.val)
            return s, n

        ans = 0
        dfs(root)
        return ans
