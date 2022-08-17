# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        q = deque([root])
        while q:
            ans = 0
            for _ in range(len(q)):
                root = q.popleft()
                ans += root.val
                if root.left:
                    q.append(root.left)
                if root.right:
                    q.append(root.right)
        return ans
