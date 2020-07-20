class Solution:
    def checkSubTree(self, t1: TreeNode, t2: TreeNode) -> bool:
        if t1 == None:
            return False
        if t2 == None:
            return True
        return self.dfs(t1,t2) or self.checkSubTree(t1.left,t2) or self.checkSubTree(t1.right,t2)
    
    def dfs(self, t1: TreeNode, t2: TreeNode) -> bool:
        if not t1 and t2 :
            return False
        if not t2 and not t1:
            return True
        if t1.val != t2.val:
            return False
        else:
            return self.dfs(t1.left,t2.left) and self.dfs(t1.right,t2.right)