# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def reverseOddLevels(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        q = deque([root])
        i = 0
        while q:
            t = []
            for _ in range(len(q)):
                node = q.popleft()
                if i & 1:
                    t.append(node)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if t:
                j, k = 0, len(t) - 1
                while j < k:
                    t[j].val, t[k].val = t[k].val, t[j].val
                    j, k = j + 1, k - 1
            i += 1
        return root
