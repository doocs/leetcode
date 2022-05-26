/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func findRoot(tree []*Node) *Node {
	xorsum := 0
	for _, node := range tree {
		xorsum ^= node.Val
		for _, child := range node.Children {
			xorsum ^= child.Val
		}
	}
	for _, node := range tree {
		if node.Val == xorsum {
			return node
		}
	}
	return nil
}