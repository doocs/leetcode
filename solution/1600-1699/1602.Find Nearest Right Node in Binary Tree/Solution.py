# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findNearestRightNode(self, root: TreeNode, u: TreeNode) -> Optional[TreeNode]:
        q = deque([root])
        while q:
            for i in range(len(q) - 1, -1, -1):
                root = q.popleft()
                if root == u:
                    return q[0] if i else None
                if root.left:
                    q.append(root.left)
                if root.right:
                    q.append(root.right)
