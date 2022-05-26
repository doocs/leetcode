# Definition for a binary tree node.
# class Node(object):
#     def __init__(self, val=" ", left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEquivalence(self, root1: 'Node', root2: 'Node') -> bool:
        def calc(ans, left, right, op):
            for i in range(26):
                if op == '+':
                    ans[i] = left[i] + right[i]
                else:
                    ans[i] = left[i] - right[i]

        def dfs(root):
            ans = [0] * 26
            if not root:
                return ans
            if root.val in ['+', '-']:
                left, right = dfs(root.left), dfs(root.right)
                calc(ans, left, right, root.val)
            else:
                ans[ord(root.val) - ord('a')] += 1
            return ans

        return dfs(root1) == dfs(root2)
