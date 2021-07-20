/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func cloneTree(root *Node) *Node {
	if root == nil {
		return nil
	}
	node := &Node{Val: root.Val}
	for _, child := range root.Children {
		node.Children = append(node.Children, cloneTree(child))
	}
	return node
}