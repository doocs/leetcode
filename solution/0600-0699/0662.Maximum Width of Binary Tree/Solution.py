# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def widthOfBinaryTree(self, root: TreeNode) -> int:
        q = deque([(root, 1)])
        ans = 0
        while q:
            n = len(q)
            ans = max(ans, q[-1][1] - q[0][1] + 1)
            for _ in range(n):
                node, j = q.popleft()
                if node.left:
                    q.append((node.left, 2 * j))
                if node.right:
                    q.append((node.right, 2 * j + 1))
        return ans
