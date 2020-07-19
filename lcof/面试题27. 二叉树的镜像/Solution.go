func mirrorTree(root *TreeNode) *TreeNode {
    if root == nil {
        return root
    }
    root.Left, root.Right = root.Right, root.Left
    root.Left = mirrorTree(root.Left)
    root.Right = mirrorTree(root.Right)
    return root
}