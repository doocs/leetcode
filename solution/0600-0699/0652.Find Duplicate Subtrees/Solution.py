# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findDuplicateSubtrees(
        self, root: Optional[TreeNode]
    ) -> List[Optional[TreeNode]]:
        def dfs(root):
            if root is None:
                return '#'
            v = f'{root.val},{dfs(root.left)},{dfs(root.right)}'
            counter[v] += 1
            if counter[v] == 2:
                ans.append(root)
            return v

        ans = []
        counter = Counter()
        dfs(root)
        return ans
