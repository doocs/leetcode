# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: TreeNode, targetSum: int) -> int:
        preSum = defaultdict(int)
        preSum[0] = 1

        def dfs(node: TreeNode, cur: int) -> int:
            if not node:
                return 0

            cur += node.val
            ret = preSum[cur - targetSum]

            preSum[cur] += 1
            ret += dfs(node.left, cur)
            ret += dfs(node.right, cur)
            preSum[cur] -= 1

            return ret

        return dfs(root, 0)
