# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:

    def bstFromPreorder(self, preorder: List[int]) -> TreeNode:
        def buildtree(li):
            if not li:
                return
            val = li[0]
            if len(li) == 1:
                root = TreeNode(val)
            else:
                l, r = [], []
                for item in li[1:]:
                    if item > val:
                        r.append(item)
                    else:
                        l.append(item)
                root = TreeNode(val)
                root.left = buildtree(l)
                root.right = buildtree(r)
            return root
        return buildtree(preorder)
