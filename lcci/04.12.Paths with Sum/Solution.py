class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> int:
        def dfs(root, sum, flag):
            nonlocal ans
            if not root:
                return 0
            if sum - root.val == 0:
                ans += 1
            if flag == 0:
                dfs(root.left, sum, 0)
                dfs(root.right, sum, 0)
            dfs(root.left, sum - root.val, 1)
            dfs(root.right, sum - root.val, 1)

        if not root:
            return 0
        ans = 0
        dfs(root, sum, 0)
        return ans
