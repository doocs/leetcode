# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def verticalTraversal(self, root: TreeNode) -> List[List[int]]:
        def dfs(root, i, j):
            if root is None:
                return
            nodes.append((i, j, root.val))
            dfs(root.left, i + 1, j - 1)
            dfs(root.right, i + 1, j + 1)

        nodes = []
        dfs(root, 0, 0)
        nodes.sort(key=lambda x: (x[1], x[0], x[2]))
        ans = []
        prev = -2000
        for i, j, v in nodes:
            if prev != j:
                ans.append([])
                prev = j
            ans[-1].append(v)
        return ans
