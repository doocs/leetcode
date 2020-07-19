func isSymmetric(root *TreeNode) bool {
    if root == nil {
        return true
    }
    return isSymme(root.Left, root.Right)
}

func isSymme(a *TreeNode, b *TreeNode) bool {
    if a == nil && b == nil {
        return true
    }
    if a == nil || b ==nil {
        return false
    }
    if a.Val != b.Val {
        return false
    }
    return isSymme(a.Left,b.Right) && isSymme(a.Right, b.Left)
}