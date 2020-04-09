# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution(object):
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        zuo = []
        you = []
        if root is None:
            return []
        zuo.append(root)
        ans = []
        while zuo or you:
            tmp = []
            while zuo:
                if zuo[0].left is not None:
                    you.append(zuo[0].left)
                if zuo[0].right is not None:
                    you.append(zuo[0].right)
                tmp.append(zuo[0].val)
                zuo.pop(0)
            ans.append(tmp)
            tmp = []
            while you:
                if you[-1].right is not None:
                    zuo.insert(0, you[-1].right)
                if you[-1].left is not None:
                    zuo.insert(0, you[-1].left)
                tmp.append(you[-1].val)
                you.pop(-1)
            if tmp:
                ans.append(tmp)
        return ans
