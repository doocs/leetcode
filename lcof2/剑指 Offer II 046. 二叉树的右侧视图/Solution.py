# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: TreeNode) -> List[int]:
        ans = []
        if not root:
            return ans
        d = deque([root])
        while d:
            n = len(d)
            ans.append(d[0].val)
            for i in range(n):
                node = d.popleft()
                if node.right:
                    d.append(node.right)
                if node.left:
                    d.append(node.left)
        return ans
