/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 func isBalanced(root *TreeNode) bool {
    if (root == nil) {
        return true
    }
    return math.Abs(float64(depth(root.Left)-depth(root.Right))) <= 1 && isBalanced(root.Left) && isBalanced(root.Right)
}

func depth(root *TreeNode) int {
    if (root == nil) {
        return 0
    }
    left, right := depth(root.Left), depth(root.Right)
    if (left > right) {
        return 1 + left
    }
    return 1 + right
}