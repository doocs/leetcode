# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def generateTrees(self, n: int) -> List[TreeNode]:
        def gen(left, right):
            ans = []
            if left > right:
                ans.append(None)
            else:
                for i in range(left, right + 1):
                    left_trees = gen(left, i - 1)
                    right_trees = gen(i + 1, right)
                    for l in left_trees:
                        for r in right_trees:
                            node = TreeNode(i, l, r)
                            ans.append(node)
            return ans

        return gen(1, n)
