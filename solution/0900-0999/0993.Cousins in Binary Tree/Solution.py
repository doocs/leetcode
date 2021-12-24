# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: TreeNode, x: int, y: int) -> bool:
        p = list(range(110))
        d = list(range(110))
        q = deque([root])
        i = 0
        while q:
            n = len(q)
            for _ in range(n):
                node = q.popleft()
                d[node.val] = i
                if node.left:
                    p[node.left.val] = node.val
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
                    p[node.right.val] = node.val
            i += 1
        return p[x] != p[y] and d[x] == d[y]
