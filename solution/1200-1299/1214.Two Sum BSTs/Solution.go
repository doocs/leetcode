/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func twoSumBSTs(root1 *TreeNode, root2 *TreeNode, target int) bool {
	vals1 := inorder(root1)
	vals2 := inorder(root2)
	i, j := 0, len(vals2)-1
	for i < len(vals1) && j >= 0 {
		s := vals1[i] + vals2[j]
		if s == target {
			return true
		}
		if s < target {
			i++
		} else {
			j--
		}
	}
	return false
}

func inorder(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	left := inorder(root.Left)
	right := inorder(root.Right)
	return append(append(left, root.Val), right...)
}