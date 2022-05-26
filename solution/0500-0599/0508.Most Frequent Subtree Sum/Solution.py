# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findFrequentTreeSum(self, root: TreeNode) -> List[int]:
        def dfs(root):
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            s = root.val + left + right
            counter[s] += 1
            return s

        counter = Counter()
        dfs(root)
        mx = max(counter.values())
        return [k for k, v in counter.items() if v == mx]
