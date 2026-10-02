# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class BST:
    def __init__(self, root):
        self.cnt = {}
        self.root = root
        if root is not None:
            stack = [(root, False)]
            while stack:
                node, visited = stack.pop()
                if not visited:
                    stack.append((node, True))
                    if node.right:
                        stack.append((node.right, False))
                    if node.left:
                        stack.append((node.left, False))
                else:
                    self.cnt[id(node)] = 1 + self.cnt.get(id(node.left), 0) + self.cnt.get(id(node.right), 0)

    def kthSmallest(self, k):
        node = self.root
        while node:
            left_count = self.cnt.get(id(node.left), 0)
            if left_count == k - 1:
                return node.val
            if left_count < k - 1:
                k -= left_count + 1
                node = node.right
            else:
                node = node.left
        return 0


class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        bst = BST(root)
        return bst.kthSmallest(k)
