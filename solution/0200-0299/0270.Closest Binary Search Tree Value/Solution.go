import "math"

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func closestValue(root *TreeNode, target float64) int {
	res := root.Val
	minDiff := math.Abs(float64(root.Val) - float64(target))
	for root != nil {
		val := math.Abs(float64(root.Val) - float64(target))
		if minDiff > val {
			minDiff = val
			res = root.Val
		}
		if float64(root.Val) > target {
			root = root.Left
		} else {
			root = root.Right
		}
	}
	return res
}