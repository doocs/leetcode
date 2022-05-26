# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getAllElements(self, root1: TreeNode, root2: TreeNode) -> List[int]:
        def dfs(root, t):
            if root is None:
                return
            dfs(root.left, t)
            t.append(root.val)
            dfs(root.right, t)

        def merge(t1, t2):
            ans = []
            i = j = 0
            while i < len(t1) and j < len(t2):
                if t1[i] <= t2[j]:
                    ans.append(t1[i])
                    i += 1
                else:
                    ans.append(t2[j])
                    j += 1
            while i < len(t1):
                ans.append(t1[i])
                i += 1
            while j < len(t2):
                ans.append(t2[j])
                j += 1
            return ans

        t1, t2 = [], []
        dfs(root1, t1)
        dfs(root2, t2)
        return merge(t1, t2)
