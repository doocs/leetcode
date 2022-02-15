# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class FindElements:
    def __init__(self, root: TreeNode):
        root.val = 0
        self.nodes = {0}

        def dfs(root):
            if root is None:
                return
            if root.left:
                root.left.val = root.val * 2 + 1
                self.nodes.add(root.left.val)
            if root.right:
                root.right.val = root.val * 2 + 2
                self.nodes.add(root.right.val)
            dfs(root.left)
            dfs(root.right)

        dfs(root)

    def find(self, target: int) -> bool:
        return target in self.nodes


# Your FindElements object will be instantiated and called as such:
# obj = FindElements(root)
# param_1 = obj.find(target)
