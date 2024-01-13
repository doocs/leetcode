# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator:
    def __init__(self, root: Optional[TreeNode]):
        self.nums = []

        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            self.nums.append(root.val)
            dfs(root.right)

        dfs(root)
        self.i = -1

    def hasNext(self) -> bool:
        return self.i < len(self.nums) - 1

    def next(self) -> int:
        self.i += 1
        return self.nums[self.i]

    def hasPrev(self) -> bool:
        return self.i > 0

    def prev(self) -> int:
        self.i -= 1
        return self.nums[self.i]


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.hasNext()
# param_2 = obj.next()
# param_3 = obj.hasPrev()
# param_4 = obj.prev()
