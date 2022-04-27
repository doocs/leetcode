# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def createBinaryTree(self, descriptions: List[List[int]]) -> Optional[TreeNode]:
        g = defaultdict(TreeNode)
        vis = set()
        for p, c, left in descriptions:
            if p not in g:
                g[p] = TreeNode(p)
            if c not in g:
                g[c] = TreeNode(c)
            if left:
                g[p].left = g[c]
            else:
                g[p].right = g[c]
            vis.add(c)
        for v, node in g.items():
            if v not in vis:
                return node
