# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def constructFromPrePost(self, pre, post):
        """
        :type pre: List[int]
        :type post: List[int]
        :rtype: TreeNode
        """
        if pre:
            root = TreeNode(pre[0])
            if len(pre) == 1:
                return root
            else:
                for i in range(0, len(pre) - 1):
                    if post[i] == pre[1]:
                        root.left = self.constructFromPrePost(
                            pre[1:i + 1 + 1], post[:i + 1])
                        root.right = self.constructFromPrePost(
                            pre[i + 1 + 1:], post[i + 1:-1])
                        break
                return root
        else:
            return None
