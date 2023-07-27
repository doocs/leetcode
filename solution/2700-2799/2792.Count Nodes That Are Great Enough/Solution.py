# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countGreatEnoughNodes(self, root: Optional[TreeNode], k: int) -> int:
        def push(pq, x):
            heappush(pq, x)
            if len(pq) > k:
                heappop(pq)

        def dfs(root):
            if root is None:
                return []
            l, r = dfs(root.left), dfs(root.right)
            for x in r:
                push(l, x)
            if len(l) == k and -l[0] < root.val:
                nonlocal ans
                ans += 1
            push(l, -root.val)
            return l

        ans = 0
        dfs(root)
        return ans
