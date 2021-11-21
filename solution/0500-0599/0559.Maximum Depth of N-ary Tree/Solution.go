/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func maxDepth(root *Node) int {
	if root == nil {
		return 0
	}
	ans := 1
	for _, child := range root.Children {
		ans = max(ans, 1+maxDepth(child))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}