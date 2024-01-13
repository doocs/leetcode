# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def printTree(self, root: Optional[TreeNode]) -> List[List[str]]:
        def height(root):
            q = deque([root])
            h = -1
            while q:
                h += 1
                for _ in range(len(q)):
                    root = q.popleft()
                    if root.left:
                        q.append(root.left)
                    if root.right:
                        q.append(root.right)
            return h

        h = height(root)
        m, n = h + 1, 2 ** (h + 1) - 1
        ans = [[""] * n for _ in range(m)]
        q = deque([(root, 0, (n - 1) // 2)])
        while q:
            node, r, c = q.popleft()
            ans[r][c] = str(node.val)
            if node.left:
                q.append((node.left, r + 1, c - 2 ** (h - r - 1)))
            if node.right:
                q.append((node.right, r + 1, c + 2 ** (h - r - 1)))
        return ans
