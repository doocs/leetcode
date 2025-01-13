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
	mx := 0
	for _, child := range root.Children {
		mx = max(mx, maxDepth(child))
	}
	return 1 + mx
}
