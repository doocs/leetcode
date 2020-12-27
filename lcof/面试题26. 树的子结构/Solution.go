/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 func isSubStructure(A *TreeNode, B *TreeNode) bool {
    // 约定空树不是任意一个树的子结构
    if A == nil || B == nil {
        return false
    }
    return helper(A,B) || isSubStructure(A.Left,B) || isSubStructure(A.Right,B) 
}

func helper(a *TreeNode, b *TreeNode) bool {
    if b ==  nil {
        return true
    }
    if a == nil {
        return false
    }
    return a.Val == b.Val && helper(a.Left, b.Left) && helper(a.Right, b.Right)
}