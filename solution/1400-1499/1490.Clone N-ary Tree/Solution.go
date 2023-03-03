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
	children := []*Node{}
	for _, child := range root.Children {
		children = append(children, cloneTree(child))
	}
	return &Node{root.Val, children}
}