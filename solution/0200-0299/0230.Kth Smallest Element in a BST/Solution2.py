# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class BST:
    def __init__(self, root):
        self.cnt = Counter()
        self.root = root
        self.count(root)

    def kthSmallest(self, k):
        node = self.root
        while node:
            if self.cnt[node.left] == k - 1:
                return node.val
            if self.cnt[node.left] < k - 1:
                k -= self.cnt[node.left] + 1
                node = node.right
            else:
                node = node.left
        return 0

    def count(self, root):
        if root is None:
            return 0
        n = 1 + self.count(root.left) + self.count(root.right)
        self.cnt[root] = n
        return n


class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        bst = BST(root)
        return bst.kthSmallest(k)
