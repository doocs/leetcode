# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def level(self, nodes):
        """
        :type nodes: List[TreeNode]
        :rtype: List[List[int]]
        """
        Ret = [[]]
        Next_Level = []
        
        for each in nodes:
            Ret[0].append(each.val)
            
            if(each.left != None):
                Next_Level.append(each.left)
            if(each.right != None):
                Next_Level.append(each.right)
                
        if Next_Level :
            Ret.extend(self.level(Next_Level))
        return Ret
    
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        
        if(root != None):
            return self.level([root])
        else:
            return []
