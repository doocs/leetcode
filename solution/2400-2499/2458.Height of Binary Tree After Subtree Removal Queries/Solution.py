# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def treeQueries(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:
        def f(root):
            if root is None:
                return 0
            l, r = f(root.left), f(root.right)
            d[root] = 1 + max(l, r)
            return d[root]

        def dfs(root, depth, rest):
            if root is None:
                return
            depth += 1
            res[root.val] = rest
            dfs(root.left, depth, max(rest, depth + d[root.right]))
            dfs(root.right, depth, max(rest, depth + d[root.left]))

        d = defaultdict(int)
        f(root)
        res = [0] * (len(d) + 1)
        dfs(root, -1, 0)
        return [res[v] for v in queries]
