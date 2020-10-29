func sumNumbers(root *TreeNode) int {
    if root == nil {
        return 0
    }
    return dfs(root, 0)
}

func dfs(root *TreeNode, sum int) int {
    if root == nil {
        return 0
    }
    if root.Left == nil && root.Right == nil {
        return 10*sum + root.Val
    }
    return dfs(root.Left, 10*sum + root.Val) + dfs(root.Right, 10*sum + root.Val)
}