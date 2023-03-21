# Definition for a binary tree node.
# class Node(object):
#     def __init__(self, val=" ", left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def checkEquivalence(self, root1: 'Node', root2: 'Node') -> bool:
        def dfs(root):
            cnt = [0] * 26
            if root is None:
                return cnt
            if root.val in '+-':
                l, r = dfs(root.left), dfs(root.right)
                k = 1 if root.val == '+' else -1
                for i in range(26):
                    cnt[i] += l[i] + r[i] * k
            else:
                cnt[ord(root.val) - ord('a')] += 1
            return cnt

        return dfs(root1) == dfs(root2)
