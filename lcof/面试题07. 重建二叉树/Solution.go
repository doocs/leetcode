/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func buildTree(preorder []int, inorder []int) *TreeNode {
    return helper(preorder, inorder, 0, 0, len(preorder)-1)
}

func helper(preorder, inorder []int, index, start, end int) *TreeNode {
    if start > end {
        return nil
    }
    root := &TreeNode{Val:preorder[index]}
    j := start
    for j < end && preorder[index] != inorder[j] {
        j++
    }
    root.Left = helper(preorder, inorder, index + 1, start, j - 1)
    root.Right = helper(preorder, inorder, index + 1 + j -start, j + 1, end)
    return root
}