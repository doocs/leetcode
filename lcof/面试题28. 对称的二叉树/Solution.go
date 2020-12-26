/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 func isSymmetric(root *TreeNode) bool {
    if root == nil {
        return true
    }
    return isSymme(root.Left, root.Right)
}

func isSymme(left *TreeNode, right *TreeNode) bool {
    if left == nil && right == nil {
        return true
    }
    if left == nil || right == nil || left.Val != right.Val {
        return false
    }
    return isSymme(left.Left, right.Right) && isSymme(left.Right, right.Left)
}