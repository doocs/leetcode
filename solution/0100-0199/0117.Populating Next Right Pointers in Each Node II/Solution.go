/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Next *Node
 * }
 */

func connect(root *Node) *Node {
	node := root
	var prev, next *Node
	modify := func(curr *Node) {
		if curr == nil {
			return
		}
		if next == nil {
			next = curr
		}
		if prev != nil {
			prev.Next = curr
		}
		prev = curr
	}
	for node != nil {
		prev, next = nil, nil
		for node != nil {
			modify(node.Left)
			modify(node.Right)
			node = node.Next
		}
		node = next
	}
	return root
}