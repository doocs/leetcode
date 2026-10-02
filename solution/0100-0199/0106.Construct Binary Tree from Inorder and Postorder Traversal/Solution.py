# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        root = TreeNode(postorder[-1])
        stack = [root]
        inorder_index = len(inorder) - 1
        for value in reversed(postorder[:-1]):
            node = stack[-1]
            if node.val != inorder[inorder_index]:
                node.right = TreeNode(value)
                stack.append(node.right)
            else:
                while stack and stack[-1].val == inorder[inorder_index]:
                    node = stack.pop()
                    inorder_index -= 1
                node.left = TreeNode(value)
                stack.append(node.left)
        return root
