class Solution:
    def maxValue(self, root: TreeNode, k: int) -> int:
        def dp(node):
            if node.left:
                if node.right: return cg(node.val, dp(node.left), dp(node.right))  # 左右都存在
                else: return cg(node.val, dp(node.left))  # 单左存在
            elif node.right:
                return cg(node.val, dp(node.right))  # 单右存在
            else: return [0] + [node.val] * k  # 左右都不存在
        def cg(v,lis0,lis1=None):
            nlis = [0] * (k + 1)
            if lis1:
                nlis[0] = lis0[-1]+lis1[-1]
                for i in range(k):
                    nlis[i+1] = nlis[i]
                    for j in range(i+1):
                        nlis[i+1] = max(nlis[i+1],lis0[j]+lis1[i-j]+v)
            else:
                nlis[0] = lis0[-1]
                for i in range(k):
                    nlis[i+1] = max(nlis[i],lis0[i]+v)
            return nlis
        v = root.val
        if root.left:
            if root.right:  # 左右都存在
                left = dp(root.left)
                right = dp(root.right)
                mx = left[-1]+right[-1]
                for i in range(k):
                    mx = max(mx, left[i]+right[k-i-1]+v)
                return mx
            else:  # 单左存在
                *_,x,y = dp(root.left)
        elif root.right: *_,x,y = dp(root.right)    # 单右存在
        else: return v  # 左右都不存在
        return max(y, v+x)  # 单孩子情况的返回值
