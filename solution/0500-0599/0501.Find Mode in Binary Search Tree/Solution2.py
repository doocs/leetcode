# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findMode(self, root: TreeNode) -> List[int]:
        prev = None
        mx = cnt = 0
        ans = []
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.left, 0))
                continue
            cnt = cnt + 1 if prev == node.val else 1
            if cnt > mx:
                ans = [node.val]
                mx = cnt
            elif cnt == mx:
                ans.append(node.val)
            prev = node.val
            stk.append((node.right, 0))
        return ans
